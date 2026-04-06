package com.juanCarlos.hardwareHub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Payload para crear o recuperar una conversación con otro usuario")
public class CreateConversationRequestDto {

    @NotNull
    @Schema(description = "ID del usuario con el que iniciar la conversación", example = "3")
    private Long targetUserId;
}

