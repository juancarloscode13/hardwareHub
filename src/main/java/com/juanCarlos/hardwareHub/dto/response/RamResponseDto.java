package com.juanCarlos.hardwareHub.dto.response;

import com.juanCarlos.hardwareHub.entity.enums.RamTipo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de respuesta de un módulo de RAM")
public class RamResponseDto {

    @Schema(description = "Identificador único", example = "1")
    private Long id;
    @Schema(description = "Nombre del modelo", example = "Corsair Vengeance DDR5")
    private String modelo;
    @Schema(description = "ID del fabricante", example = "3")
    private Long fabricanteId;
    @Schema(description = "Precio en euros", example = "119.99")
    private BigDecimal precio;
    @Schema(description = "Velocidad en MHz", example = "6000")
    private Integer velocidad;
    @Schema(description = "Capacidad total en GB", example = "32")
    private Integer cantidad;
    @Schema(description = "Número de módulos en el kit", example = "2")
    private Integer modulos;
    @Schema(description = "Capacidad por módulo en GB", example = "16")
    private Integer capacidadPorModulo;
    @Schema(description = "Tipo de memoria RAM", example = "DDR5")
    private RamTipo tipo;
    @Schema(description = "Latencia CAS (CL)", example = "36")
    private Integer latencia;
}
