package com.juanCarlos.hardwareHub.dto.response;

import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.entity.enums.RefrigeracionTipo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de respuesta de un sistema de refrigeración")
public class RefrigeracionResponseDto {

    @Schema(description = "Identificador único", example = "1")
    private Long id;
    @Schema(description = "Nombre del modelo", example = "Noctua NH-D15")
    private String modelo;
    @Schema(description = "ID del fabricante", example = "8")
    private Long fabricanteId;
    @Schema(description = "Precio en euros", example = "99.99")
    private BigDecimal precio;
    @Schema(description = "Socket de CPU compatible", example = "AM5")
    private CpuSocket socketCompatible;
    @Schema(description = "Tipo de refrigeración", example = "AIRE")
    private RefrigeracionTipo tipo;
    @Schema(description = "Atributos adicionales (tamaño radiador, RPM, etc.)")
    private Map<String, Object> atributos;
}
