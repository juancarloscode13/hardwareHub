# 📡 Plan de Implementación: Sistema de Mensajería Directa

> **Stack:** Spring Boot (backend) + React + TypeScript (frontend)  
> **Autenticación:** JWT en cookie HttpOnly `access_token`  
> **Protocolo tiempo real:** WebSocket + STOMP (SimpleBroker)  
> **Persistencia:** REST paginado para historial, STOMP solo para canal en vivo

---

## 📋 Índice

1. [Modelo de datos](#1-modelo-de-datos)
2. [Fase 1 — Infraestructura WebSocket en Spring Boot](#2-fase-1--infraestructura-websocket-en-spring-boot)
3. [Fase 2 — Autenticación JWT en el handshake WebSocket](#3-fase-2--autenticación-jwt-en-el-handshake-websocket)
4. [Fase 3 — Capa de negocio backend (Entidades, Repos, Service, Controller)](#4-fase-3--capa-de-negocio-backend)
5. [Fase 4 — API REST para historial paginado](#5-fase-4--api-rest-para-historial-paginado)
6. [Fase 5 — Frontend: hook `useChat` + gestión de estado](#6-fase-5--frontend-hook-usechat--gestión-de-estado)
7. [Fase 6 — Componentes UI](#7-fase-6--componentes-ui)
8. [Fase 7 — Notificaciones globales (reutilización del canal STOMP)](#8-fase-7--notificaciones-globales-reutilización-del-canal-stomp)
9. [Orden de implementación recomendado](#9-orden-de-implementación-recomendado)
10. [Trade-offs y decisiones técnicas](#10-trade-offs-y-decisiones-técnicas)

---

## 1. Modelo de datos

### Nuevas tablas en la base de datos

```sql
-- Conversación entre dos usuarios (canal bidireccional)
CREATE TABLE conversation (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_a_id    BIGINT NOT NULL,
    user_b_id    BIGINT NOT NULL,
    created_at   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_a_id) REFERENCES usuario(id),
    FOREIGN KEY (user_b_id) REFERENCES usuario(id),
    -- Garantiza que no existan dos conversaciones duplicadas entre el mismo par
    UNIQUE KEY uq_conversation (
        LEAST(user_a_id, user_b_id),
        GREATEST(user_a_id, user_b_id)
    )
);

-- Mensajes individuales dentro de una conversación
CREATE TABLE message (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    conversation_id BIGINT NOT NULL,
    sender_id       BIGINT NOT NULL,
    content         TEXT NOT NULL,
    sent_at         DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_read         BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (conversation_id) REFERENCES conversation(id) ON DELETE CASCADE,
    FOREIGN KEY (sender_id)       REFERENCES usuario(id)
);
```

### Entidades JPA (Spring Boot)

| Entidad | Campos clave | Relaciones |
|---|---|---|
| `ConversationEntity` | `id`, `userA`, `userB`, `createdAt` | `@ManyToOne` → `UsuarioEntity` (×2), `@OneToMany` → `MessageEntity` |
| `MessageEntity` | `id`, `content`, `sentAt`, `isRead` | `@ManyToOne` → `ConversationEntity`, `@ManyToOne` → `UsuarioEntity` (sender) |

> **Decisión de diseño:** no existe una tabla `message_read_status` separada porque en un DM 1:1 el receptor siempre es exactamente uno. El campo `is_read` en `MessageEntity` es suficiente y evita un JOIN innecesario. Si en el futuro se necesitan grupos, se refactoriza.

---

## 2. Fase 1 — Infraestructura WebSocket en Spring Boot

### 2.1 Dependencias (`pom.xml`)

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

> `spring-messaging` viene incluido transitivamente con `spring-boot-starter-websocket`. No hace falta añadirlo por separado.

### 2.2 Configuración STOMP (`WebSocketConfig.java`)

**Paquete:** `config/`

```
config/
└── WebSocketConfig.java   ← nuevo
```

**Responsabilidades de la clase:**
- Implementar `WebSocketMessageBrokerConfigurer`
- Registrar el endpoint `/ws` con soporte SockJS (fallback HTTP long-polling)
- Configurar el `SimpleBroker` con destinos `/queue` y `/topic`
- Definir el prefijo de aplicación `/app` para los `@MessageMapping`
- Configurar el `UserDestinationPrefix` como `/user`

**Diagrama de flujo STOMP:**

```
Cliente → /app/chat.send       → @MessageMapping  → broker  → /user/{id}/queue/messages
Cliente → /app/chat.read       → @MessageMapping  → marca leído en DB
Cliente ←  SimpleBroker       ← push mensaje nuevo al receptor en tiempo real
```

### 2.3 Permitir `/ws` en SecurityConfig

Añadir `/ws/**` a las rutas públicas del `SecurityFilterChain` existente. El handshake HTTP que abre el WebSocket no puede pasar por el filtro JWT estándar — la autenticación para WebSocket se gestiona en la Fase 2 mediante un `HandshakeInterceptor` propio.

```
PUBLIC_PATHS actuales:
  /auth/login, /auth/logout, /auth/register, /auth/refresh, /api/noticias

Añadir:
  /ws/**           ← endpoint de upgrade WebSocket (SockJS lo usa también como HTTP)
```

---

## 3. Fase 2 — Autenticación JWT en el handshake WebSocket

> Esta es la parte más delicada de toda la feature. Resuélvela en aislado antes de construir nada encima.

### ¿Por qué es especial aquí?

El `JwtAuthenticationFilter` existente extiende `OncePerRequestFilter` y actúa sobre el ciclo de vida HTTP normal. El upgrade HTTP→WebSocket ocurre en **una sola petición especial** que Spring Security no procesa igual. Una vez establecido el canal WS, ya no hay requests HTTP, así que el filtro no vuelve a ejecutarse.

### La buena noticia: tu setup ya lo facilita

Tu proyecto usa JWT en cookie HttpOnly `access_token` con `allowCredentials(true)` y origen explícito `http://localhost:5173`. Esto significa que **la cookie se enviará automáticamente en el handshake SockJS** (es una petición HTTP cross-origin con credenciales). No necesitas pasar el token manualmente en headers ni por query param.

### Solución: `JwtHandshakeInterceptor.java`

**Paquete:** `security/`

```
security/
├── JwtAuthenticationFilter.java   ← existente, no se toca
├── SecurityConfig.java            ← se modifica (añadir /ws/**)
├── JwtHandshakeInterceptor.java   ← nuevo
└── ...
```

**Responsabilidades:**
- Implementar `HandshakeInterceptor`
- En `beforeHandshake()`: leer la cookie `access_token` de los headers HTTP del handshake
- Validar el token con el `JwtService` existente
- Si es válido: cargar el `UserDetails` y establecer `Authentication` en los atributos del WebSocket session (`attributes.put("user", authentication)`)
- Si no es válido: retornar `false` → el handshake se rechaza con 401

**Registrar el interceptor en `WebSocketConfig`:**

```
.addEndpoint("/ws")
    .addInterceptors(jwtHandshakeInterceptor)   ← inyectado por Spring
    .withSockJS()
```

**Registrar el `Authentication` en el canal STOMP:**

Implementar `ChannelInterceptor` (`StompAuthChannelInterceptor.java`) para que en el mensaje `CONNECT` de STOMP, Spring extraiga el `Authentication` de los atributos de sesión y lo establezca en el `SecurityContextHolder`. Esto es lo que permite usar `@AuthenticationPrincipal` dentro de los `@MessageMapping`.

```
security/
└── StompAuthChannelInterceptor.java   ← nuevo
```

### Flujo completo de autenticación WS

```
1. Frontend hace upgrade HTTP → /ws (SockJS)
   └── Cookie access_token enviada automáticamente
2. JwtHandshakeInterceptor lee la cookie, valida JWT
   ├── Token inválido → handshake rechazado (403)
   └── Token válido  → Authentication guardada en atributos de sesión WS
3. Cliente envía CONNECT frame STOMP
4. StompAuthChannelInterceptor extrae Authentication de sesión
   └── SecurityContextHolder poblado para ese canal
5. A partir de aquí, @AuthenticationPrincipal funciona en @MessageMapping
```

---

## 4. Fase 3 — Capa de negocio backend

### 4.1 Estructura de paquetes (solo lo nuevo)

```
com/juanCarlos/hardwareHub/
├── entity/
│   ├── ConversationEntity.java    ← nueva
│   └── MessageEntity.java         ← nueva
├── repository/
│   ├── ConversationRepository.java ← nueva
│   └── MessageRepository.java      ← nueva
├── dto/
│   ├── MessageDTO.java             ← nueva (entrada/salida WS y REST)
│   ├── ConversationDTO.java        ← nueva (resumen de conversación con último mensaje)
│   └── SendMessageRequest.java     ← nueva (payload del @MessageMapping)
├── service/
│   └── ChatService.java            ← nueva
└── controller/
    ├── ChatRestController.java     ← nueva (historial paginado)
    └── ChatWebSocketController.java ← nueva (@MessageMapping)
```

### 4.2 `ConversationRepository`

Consultas clave:
- `findByUserAAndUserB` + `findByUserBAndUserA` → buscar conversación existente entre dos usuarios (en ambas direcciones)
- `findAllByUserAOrUserB(usuario, usuario)` → listar todas las conversaciones de un usuario
- `findOrCreate` (lógica en service) → obtener conversación existente o crearla si no existe

### 4.3 `MessageRepository`

Consultas clave:
- `findByConversationIdOrderBySentAtAsc(conversationId, Pageable)` → historial paginado
- `countByConversationIdAndSenderIdNotAndIsReadFalse(conversationId, currentUserId)` → mensajes no leídos
- `markAllAsRead(conversationId, currentUserId)` → `@Modifying @Query` UPDATE masivo

### 4.4 `ChatService`

Responsabilidades (sin mezclar capas):
- `getOrCreateConversation(userAId, userBId)` → idempotente, crea si no existe
- `sendMessage(conversationId, senderId, content)` → persiste el mensaje, retorna el DTO
- `getHistory(conversationId, Pageable)` → delega en repo, retorna página de DTOs
- `markAsRead(conversationId, readerId)` → UPDATE masivo en repo
- `getConversationsForUser(userId)` → lista conversaciones con resumen (último mensaje + unread count)

### 4.5 `ChatWebSocketController`

```
@MessageMapping("/chat.send")
→ Recibe SendMessageRequest
→ Llama a ChatService.sendMessage()
→ Convierte a MessageDTO
→ messagingTemplate.convertAndSendToUser(recipientId, "/queue/messages", messageDTO)
→ messagingTemplate.convertAndSendToUser(senderId, "/queue/messages", messageDTO)  ← echo al emisor

@MessageMapping("/chat.read")
→ Recibe { conversationId }
→ Llama a ChatService.markAsRead(conversationId, currentUserId)
→ (Opcional) notifica al emisor original que el mensaje fue leído
```

---

## 5. Fase 4 — API REST para historial paginado

**Paquete:** `controller/ChatRestController.java`

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/api/conversations` | Lista todas las conversaciones del usuario autenticado con resumen |
| `GET` | `/api/conversations/{id}/messages?page=0&size=20` | Historial paginado de una conversación (más recientes primero) |
| `POST` | `/api/conversations` | Crea o recupera una conversación con otro usuario `{ targetUserId }` |
| `PATCH` | `/api/conversations/{id}/read` | Marca todos los mensajes de una conversación como leídos |

> **Por qué REST y no STOMP para el historial:** TanStack Query gestiona cache, revalidación y paginación de forma nativa con peticiones HTTP. Mezclar el historial dentro del canal STOMP complicaría el estado sin ninguna ventaja real.

---

## 6. Fase 5 — Frontend: hook `useChat` + gestión de estado

### 6.1 Dependencias

```bash
npm install @stomp/stompjs sockjs-client
npm install @types/sockjs-client -D
```

### 6.2 Separación de responsabilidades en el frontend

```
Estado del historial (pasado)  → TanStack Query + REST /api/conversations/{id}/messages
Estado en tiempo real (nuevo)  → WebSocket STOMP + estado local (useRef/useState) o Zustand
```

> No intentes meter los mensajes en tiempo real dentro de TanStack Query. La caché de RQ no está diseñada para streams de eventos push. El patrón correcto es: cargar historial con RQ, y al llegar un mensaje nuevo por WS, actualizas el estado local y opcionalmente invalidas la caché de RQ para que refetch el contador de no leídos.

### 6.3 `StompClientManager` (singleton)

**Archivo:** `src/lib/stompClient.ts`

Responsabilidades:
- Crear y mantener **una única instancia** del cliente STOMP durante toda la sesión
- Gestionar la conexión/desconexión (`activate()` / `deactivate()`)
- El cliente usa `SockJS` como `webSocketFactory` → apunta a `http://localhost:8080/ws`
- No pasar credentials en el frame CONNECT: la cookie se manda sola en el handshake
- Exponer métodos `subscribe(destination, callback)` y `publish(destination, body)`

### 6.4 `useChat(conversationId)` — hook principal

**Archivo:** `src/hooks/useChat.ts`

```typescript
// Lo que gestiona este hook:
const {
  messages,          // MessageDTO[] — combinación de historial + tiempo real
  sendMessage,       // (content: string) => void
  isConnected,       // boolean
  hasMore,           // boolean — para paginación del historial
  loadMore,          // () => void
  unreadCount,       // number
} = useChat(conversationId);
```

**Lógica interna:**
1. Carga historial inicial con `useInfiniteQuery` (TanStack Query) → `/api/conversations/{id}/messages`
2. Se suscribe al canal STOMP `/user/queue/messages` al montar
3. Al recibir un mensaje nuevo por WS: lo añade al array local si pertenece a esta conversación
4. Al montar y al recibir mensajes del otro usuario: llama a `PATCH /api/conversations/{id}/read`
5. Al desmontar: se desuscribe del canal STOMP (no desconecta el cliente global)

### 6.5 `useConversations()` — hook de lista

**Archivo:** `src/hooks/useConversations.ts`

```typescript
// Usa TanStack Query sobre GET /api/conversations
// Se invalida cuando llega un mensaje nuevo por WS (callback en StompClientManager)
const { conversations, isLoading } = useConversations();
```

---

## 7. Fase 6 — Componentes UI

### Estructura de componentes

```
src/
└── features/
    └── chat/
        ├── ConversationList.tsx     ← lista de conversaciones con badge de no leídos
        ├── ConversationListItem.tsx ← fila individual: avatar, nombre, último mensaje, timestamp
        ├── ChatWindow.tsx           ← contenedor principal del chat
        ├── MessageList.tsx          ← lista de mensajes con scroll automático al fondo
        ├── MessageBubble.tsx        ← burbuja individual: contenido, timestamp, estado leído
        ├── MessageInput.tsx         ← textarea + botón enviar
        └── ChatLayout.tsx           ← layout que combina ConversationList + ChatWindow
```

### Comportamientos clave de la UI

| Comportamiento | Implementación |
|---|---|
| Scroll automático al fondo en mensaje nuevo | `useEffect` con `ref.current.scrollIntoView()` |
| Paginación del historial (scroll hacia arriba) | `IntersectionObserver` en el primer elemento + `loadMore()` |
| Badge de no leídos en ConversationListItem | Dato de `GET /api/conversations` (campo `unreadCount`) |
| Indicador de mensaje enviado vs. entregado | Campo `isRead` en `MessageDTO` — icono simple (✓ vs ✓✓) |
| Timestamp formateado | `date-fns` o `Intl.DateTimeFormat` — no reinventes la rueda |

> **Alcance del TFG:** Los indicadores de *typing* ("está escribiendo...") quedan **fuera del scope inicial**. Se pueden añadir en un `@MessageMapping("/chat.typing")` con un `setTimeout` de limpieza, pero disparan la complejidad del estado sin aportar valor académico diferencial. Primero que funcione; luego que brille.

---

## 8. Fase 7 — Notificaciones globales (reutilización del canal STOMP)

Una vez el canal STOMP está estable, reutilizarlo para notificaciones globales es trivial y tiene todo el sentido arquitectónico.

### Canal adicional

```
/user/{id}/queue/notifications
```

### Casos de uso

| Evento | Payload |
|---|---|
| Nuevo mensaje recibido (usuario no está en esa conversación) | `{ type: "NEW_MESSAGE", conversationId, senderName }` |
| Nueva reacción en una publicación propia | `{ type: "NEW_REACTION", publicationId, reactorName }` |
| Nuevo comentario en una publicación propia | `{ type: "NEW_COMMENT", publicationId, commenterName }` |

### Hook `useNotifications()`

**Archivo:** `src/hooks/useNotifications.ts`

- Se suscribe a `/user/queue/notifications` al montar la aplicación (nivel global, no por página)
- Muestra un toast o badge en el icono de la campana
- Invalida las queries de TanStack Query correspondientes para refetch automático

---

## 9. Orden de implementación recomendado

```
╔══════════════════════════════════════════════════════════════════╗
║  PASO 1  │ Modelo de datos: SQL + entidades JPA                 ║
║          │ Verificar que Hibernate genera las tablas sin errores ║
╠══════════════════════════════════════════════════════════════════╣
║  PASO 2  │ WebSocketConfig básico (sin auth aún)                ║
║          │ Probar con Postman/WebSocket client que /ws responde  ║
╠══════════════════════════════════════════════════════════════════╣
║  PASO 3  │ JwtHandshakeInterceptor + StompAuthChannelInterceptor ║
║          │ Verificar que el handshake falla sin cookie y         ║
║          │ funciona con ella — ANTES de escribir más código      ║
╠══════════════════════════════════════════════════════════════════╣
║  PASO 4  │ ChatService + Repositorios                           ║
║          │ Tests unitarios de la lógica de negocio              ║
╠══════════════════════════════════════════════════════════════════╣
║  PASO 5  │ ChatWebSocketController (@MessageMapping)            ║
║          │ Probar flujo completo: enviar → recibir en tiempo real║
╠══════════════════════════════════════════════════════════════════╣
║  PASO 6  │ ChatRestController (historial paginado + PATCH read) ║
╠══════════════════════════════════════════════════════════════════╣
║  PASO 7  │ Frontend: StompClientManager singleton               ║
║          │ Conectar y verificar suscripción en DevTools         ║
╠══════════════════════════════════════════════════════════════════╣
║  PASO 8  │ Hooks: useChat + useConversations                    ║
║          │ Verificar combinación historial + tiempo real         ║
╠══════════════════════════════════════════════════════════════════╣
║  PASO 9  │ Componentes UI                                       ║
║          │ De menos a más: MessageBubble → MessageList →         ║
║          │ ChatWindow → ConversationList → ChatLayout           ║
╠══════════════════════════════════════════════════════════════════╣
║  PASO 10 │ Notificaciones globales (reutilización STOMP)        ║
╚══════════════════════════════════════════════════════════════════╝
```

---

## 10. Trade-offs y decisiones técnicas

| Decisión | Alternativa descartada | Justificación |
|---|---|---|
| **SockJS** como transport | WS nativo puro | SockJS garantiza que la cookie `access_token` se envía en el handshake HTTP. WS nativo no envía cookies en algunos navegadores/proxies. Para un TFG, SockJS es más seguro. |
| **SimpleBroker** en memoria | RabbitMQ / Redis | SimpleBroker es suficiente para una demo académica. No hay mensajes que sobrevivir a un reinicio del servidor. RabbitMQ añade complejidad operacional sin valor diferencial en el TFG. |
| **`is_read` en `MessageEntity`** | Tabla `message_read_status` | Un DM 1:1 siempre tiene exactamente un receptor. Una tabla separada de status tiene sentido en grupos (1:N). Aquí es overhead innecesario. |
| **Historial por REST** | Historial por STOMP | TanStack Query gestiona cache, paginación y revalidación de forma nativa con HTTP. El historial no necesita tiempo real: es datos del pasado. |
| **Un único `StompClientManager`** singleton | Múltiples clientes WS | Una sola conexión WS por usuario es eficiente. Las suscripciones se multiplexan sobre ese canal. Crear un cliente por conversación es un error clásico de principiante. |
| **Reutilizar `/user/queue/notifications`** | Canal de notificaciones separado | La infraestructura STOMP ya está montada. Añadir un destino nuevo es gratis. Tiene todo el sentido reutilizarla para reacciones y comentarios. |

---

> *"Funciona… pero está mal. Vamos a arreglarlo."*  
> — La Arquitecta Caótica

