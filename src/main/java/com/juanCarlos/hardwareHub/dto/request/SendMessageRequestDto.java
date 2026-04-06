package com.juanCarlos.hardwareHub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Payload para enviar un mensaje de chat (WS y REST)")
public class SendMessageRequestDto {

    @NotNull
    @Schema(description = "ID de la conversación destino", example = "1")
    private Long conversationId;

    @NotBlank
    @Schema(description = "Contenido del mensaje", example = "Hola, qué tal!")
    private String content;
}

