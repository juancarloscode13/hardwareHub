package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.SendMessageRequestDto;
import com.juanCarlos.hardwareHub.dto.response.MessageResponseDto;
import com.juanCarlos.hardwareHub.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Controlador STOMP para mensajería en tiempo real.
 *
 * <p>No es un {@code @RestController} — gestiona mensajes STOMP,
 * no peticiones HTTP. La lógica de negocio vive en {@link ChatService};
 * aquí solo se orquesta la persistencia y el envío al broker.</p>
 *
 * @author Juan Carlos
 */
@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Recibe un mensaje del cliente y lo reenvía al receptor en tiempo real.
     * <p>Destino de entrada: {@code /app/chat.send}</p>
     * <p>Destino de salida: {@code /user/{email}/queue/messages}</p>
     */
    @MessageMapping("/chat.send")
    public void sendMessage(@Payload SendMessageRequestDto request, Principal principal) {
        String senderEmail = principal.getName();

        // 1. Persistir el mensaje (lógica de negocio en service)
        MessageResponseDto messageDto = chatService.sendMessage(
                senderEmail, request.getConversationId(), request.getContent());

        // 2. Obtener el email del receptor
        String recipientEmail = chatService.getRecipientEmail(request.getConversationId(), senderEmail);

        // 3. Enviar al receptor en tiempo real
        messagingTemplate.convertAndSendToUser(recipientEmail, "/queue/messages", messageDto);

        // 4. Echo al emisor (para que reciba el id y sentAt del servidor)
        messagingTemplate.convertAndSendToUser(senderEmail, "/queue/messages", messageDto);
    }

    @MessageMapping("/chat.read")
    public void markAsRead(@Payload Map<String, Long> payload, Principal principal) {
        if (principal == null || payload == null || !payload.containsKey("conversationId")) {
            return;
        }

        Long conversationId = payload.get("conversationId");
        if (conversationId == null) {
            return;
        }

        String readerEmail = principal.getName();

        // Devuelve IDs realmente cambiados a leído
        List<Long> readMessageIds = chatService.markAsRead(conversationId, readerEmail);
        if (readMessageIds == null || readMessageIds.isEmpty()) {
            return;
        }

        String otherEmail = chatService.getRecipientEmail(conversationId, readerEmail);

        Map<String, Object> receipt = Map.of(
                "conversationId", conversationId,
                "messageIds", readMessageIds
        );

        // Emisor original: actualiza ✓ -> ✓✓ en tiempo real
        messagingTemplate.convertAndSendToUser(otherEmail, "/queue/read-receipts", receipt);

        // Lector: mantiene consistencia entre pestañas/sesiones
        messagingTemplate.convertAndSendToUser(readerEmail, "/queue/read-receipts", receipt);
    }

}
