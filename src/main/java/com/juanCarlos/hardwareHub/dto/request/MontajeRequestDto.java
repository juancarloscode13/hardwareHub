package com.juanCarlos.hardwareHub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de entrada para crear o actualizar un montaje de PC")
public class MontajeRequestDto {

    @Schema(description = "ID del módulo de RAM", example = "1")
    private Long ramId;
    @Schema(description = "ID del procesador (CPU)", example = "2")
    private Long cpuId;
    @Schema(description = "ID de la tarjeta gráfica (GPU)", example = "3")
    private Long gpuId;
    @Schema(description = "ID del sistema de refrigeración", example = "4")
    private Long refrigeracionId;
    @Schema(description = "ID de la caja", example = "5")
    private Long cajaId;
    @Schema(description = "ID de la placa base", example = "6")
    private Long placaBaseId;
    @Schema(description = "ID de la fuente de alimentación (PSU)", example = "7")
    private Long psuId;
    @Schema(description = "ID del almacenamiento", example = "8")
    private Long almacenamientoId;
    @Schema(description = "ID del usuario propietario del montaje", example = "1")
    private Long usuarioId;
}
