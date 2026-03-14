package com.juanCarlos.hardwareHub.dto.response;

import com.juanCarlos.hardwareHub.entity.enums.PsuCertificacion;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de respuesta de una fuente de alimentación (PSU)")
public class PsuResponseDto {

    @Schema(description = "Identificador único", example = "1")
    private Long id;
    @Schema(description = "Nombre del modelo", example = "Seasonic Focus GX-850")
    private String modelo;
    @Schema(description = "ID del fabricante", example = "6")
    private Long fabricanteId;
    @Schema(description = "Precio en euros", example = "129.99")
    private BigDecimal precio;
    @Schema(description = "Indica si la PSU es totalmente modular", example = "true")
    private Boolean modular;
    @Schema(description = "Potencia máxima en vatios", example = "850")
    private Integer potencia;
    @Schema(description = "Certificación 80 Plus", example = "GOLD")
    private PsuCertificacion certificacion;
    @Schema(description = "Factor de forma de la PSU", example = "ATX")
    private PsuFactorForma factorForma;
}
