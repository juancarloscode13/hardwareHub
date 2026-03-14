package com.juanCarlos.hardwareHub.dto.request;

import com.juanCarlos.hardwareHub.entity.enums.TipoReaccion;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de entrada para añadir o actualizar una reacción en una publicación")
public class ReaccionRequestDto {

    @Schema(description = "ID del usuario que reacciona", example = "1")
    private Long usuarioId;

    @Schema(description = "Tipo de reacción", example = "LIKE")
    private TipoReaccion tipo;
}

