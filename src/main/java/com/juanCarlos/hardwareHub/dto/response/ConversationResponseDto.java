package com.juanCarlos.hardwareHub.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Resumen de una conversación de chat")
public class ConversationResponseDto {

    @Schema(description = "Identificador único de la conversación", example = "1")
    private Long id;
    @Schema(description = "ID del otro usuario en la conversación", example = "3")
    private Long otherUserId;
    @Schema(description = "Nombre del otro usuario", example = "maria_g")
    private String otherUserNombre;
    @Schema(description = "Icono de perfil del otro usuario")
    private byte[] otherUserIconoPerfil;
    @Schema(description = "Contenido del último mensaje", example = "Hola, qué tal!")
    private String lastMessageContent;
    @Schema(description = "Fecha del último mensaje", example = "2026-03-14T12:00:00")
    private LocalDateTime lastMessageAt;
    @Schema(description = "Número de mensajes no leídos", example = "2")
    private long unreadCount;
}

