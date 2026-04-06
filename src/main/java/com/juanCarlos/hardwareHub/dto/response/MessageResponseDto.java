package com.juanCarlos.hardwareHub.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de respuesta de un mensaje de chat")
public class MessageResponseDto {

    @Schema(description = "Identificador único del mensaje", example = "1")
    private Long id;
    @Schema(description = "ID de la conversación a la que pertenece", example = "1")
    private Long conversationId;
    @Schema(description = "ID del usuario que envió el mensaje", example = "2")
    private Long senderId;
    @Schema(description = "Nombre del usuario que envió el mensaje", example = "juan_carlos")
    private String senderNombre;
    @Schema(description = "Contenido del mensaje", example = "Hola, qué tal!")
    private String content;
    @Schema(description = "Fecha y hora de envío", example = "2026-03-14T12:00:00")
    private LocalDateTime sentAt;
    @Schema(description = "Indica si el receptor ya leyó el mensaje")
    private boolean read;
}

