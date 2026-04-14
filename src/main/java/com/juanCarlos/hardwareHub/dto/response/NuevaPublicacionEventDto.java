package com.juanCarlos.hardwareHub.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO de evento STOMP para notificar a los clientes conectados que
 * se ha creado una nueva publicación en el foro.
 *
 * <p>Intencionadamente ligero: <strong>NO incluye {@code byte[] multimedia}</strong>
 * para evitar enviar megas de datos a través del broker solo para un toast.</p>
 *
 * <p>Topic de destino: {@code /topic/forum.feed}</p>
 *
 * @author Juan Carlos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Evento de nueva publicación enviado por WebSocket/STOMP")
public class NuevaPublicacionEventDto {

    @Schema(description = "ID de la publicación recién creada", example = "42")
    private Long id;

    @Schema(description = "ID del usuario que creó la publicación", example = "7")
    private Long usuarioId;

    @Schema(description = "Nombre del autor para mostrar en el toast", example = "JuanCarlos")
    private String autorNombre;

    @Schema(description = "Alias del nombre del autor para compatibilidad con frontend", example = "JuanCarlos")
    private String nombreUsuario;

    @Schema(description = "Preview del contenido (máx. 100 caracteres)", example = "¡Nuevo montaje con RTX 5090 y Ryzen 9950X!")
    private String preview;

    @Schema(description = "Fecha y hora de creación", example = "2026-04-14T10:30:00")
    private LocalDateTime fecha;
}

