package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.response.ComentarioResponseDto;
import com.juanCarlos.hardwareHub.dto.response.NuevaPublicacionEventDto;
import com.juanCarlos.hardwareHub.dto.response.ReaccionConteoDto;
import com.juanCarlos.hardwareHub.entity.PublicacionEntity;
import com.juanCarlos.hardwareHub.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de publicar eventos de foro al broker STOMP.
 *
 * <p>Centraliza todas las llamadas a {@link SimpMessagingTemplate} relacionadas
 * con el foro, evitando acoplar la infraestructura de WebSocket directamente
 * en los servicios de negocio.</p>
 *
 * <ul>
 *   <li>{@code /topic/forum.feed} — nueva publicación (broadcast global)</li>
 *   <li>{@code /topic/publicacion.{id}.reacciones} — contadores actualizados</li>
 *   <li>{@code /topic/publicacion.{id}.comentarios} — nuevo comentario</li>
 * </ul>
 *
 * @author Juan Carlos
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ForumEventPublisher {

    /** Topic global al que se suscriben todos los usuarios en el listado del foro. */
    private static final String TOPIC_FEED = "/topic/forum.feed";

    /** Prefijo de topic para reacciones de una publicación concreta. */
    private static final String TOPIC_REACCIONES_PREFIX = "/topic/publicacion.";

    /** Sufijo de topic para reacciones. */
    private static final String TOPIC_REACCIONES_SUFFIX = ".reacciones";

    /** Sufijo de topic para comentarios. */
    private static final String TOPIC_COMENTARIOS_SUFFIX = ".comentarios";

    private final SimpMessagingTemplate messagingTemplate;
    private final UsuarioRepository usuarioRepository;

    /**
     * Publica un evento en {@code /topic/forum.feed} cuando se crea una nueva publicación.
     *
     * <p>Construye un payload ligero con los datos mínimos para el toast del frontend,
     * excluyendo el campo {@code multimedia} para no saturar el broker.</p>
     *
     * @param entity entidad recién persistida en base de datos
     */
    public void publishNuevaPublicacion(PublicacionEntity entity) {
        try {
            String contenido = entity.getContenidoTexto();
            String nombreAutor = resolveAuthorName(entity);
            // Preview de máximo 100 caracteres para el toast
            String preview = (contenido != null && contenido.length() > 100)
                    ? contenido.substring(0, 100) + "..."
                    : contenido;

            NuevaPublicacionEventDto event = new NuevaPublicacionEventDto();
            event.setId(entity.getId());
            event.setUsuarioId(entity.getUsuario().getId());
            event.setAutorNombre(nombreAutor);
            event.setNombreUsuario(nombreAutor);
            event.setPreview(preview);
            event.setFecha(entity.getFecha());

            messagingTemplate.convertAndSend(TOPIC_FEED, event);
            log.info("Evento nueva publicación publicado en {}: id={}, usuarioId={}",
                    TOPIC_FEED, entity.getId(), entity.getUsuario().getId());
        } catch (Exception e) {
            // No propagamos el error: un fallo en el broadcast no debe
            // revertir la transacción de creación de la publicación
            log.warn("No se pudo publicar el evento de nueva publicación (id={}): {}", entity.getId(), e.getMessage());
        }
    }

    /**
     * Publica los contadores de reacciones actualizados en
     * {@code /topic/publicacion.{id}.reacciones}.
     *
     * @param conteoDto DTO con los contadores calculados tras la operación
     */
    public void publishReaccionUpdate(ReaccionConteoDto conteoDto) {
        try {
            String topic = TOPIC_REACCIONES_PREFIX + conteoDto.getPublicacionId() + TOPIC_REACCIONES_SUFFIX;
            messagingTemplate.convertAndSend(topic, conteoDto);
            log.info("Evento reacción publicado en {}", topic);
        } catch (Exception e) {
            log.warn("No se pudo publicar el evento de reacción (publicacionId={}): {}",
                    conteoDto.getPublicacionId(), e.getMessage());
        }
    }

    /**
     * Publica un nuevo comentario en {@code /topic/publicacion.{id}.comentarios}.
     *
     * @param comentarioDto DTO del comentario recién persistido
     */
    public void publishNuevoComentario(ComentarioResponseDto comentarioDto) {
        try {
            String topic = TOPIC_REACCIONES_PREFIX + comentarioDto.getPublicacionId() + TOPIC_COMENTARIOS_SUFFIX;
            messagingTemplate.convertAndSend(topic, comentarioDto);
            log.info("Evento nuevo comentario publicado en {}: id={}", topic, comentarioDto.getId());
        } catch (Exception e) {
            log.warn("No se pudo publicar el evento de comentario (publicacionId={}): {}",
                    comentarioDto.getPublicacionId(), e.getMessage());
        }
    }

    /**
     * Obtiene el nombre del autor desde la entidad y, si llega nulo,
     * hace fallback a base de datos usando el id del usuario.
     */
    private String resolveAuthorName(PublicacionEntity entity) {
        if (entity.getUsuario() == null) {
            return null;
        }

        String nombreEnEntidad = entity.getUsuario().getNombre();
        if (nombreEnEntidad != null && !nombreEnEntidad.isBlank()) {
            return nombreEnEntidad;
        }

        Long userId = entity.getUsuario().getId();
        if (userId == null) {
            return null;
        }

        return usuarioRepository.findById(userId)
                .map(usuario -> usuario.getNombre())
                .orElse(null);
    }
}

