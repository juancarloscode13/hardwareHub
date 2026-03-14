package com.juanCarlos.hardwareHub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de entrada para crear o actualizar un fabricante")
public class FabricanteRequestDto {

    @Schema(description = "Nombre del fabricante", example = "AMD")
    private String nombre;
}
