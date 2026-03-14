package com.juanCarlos.hardwareHub.dto.response;

import com.juanCarlos.hardwareHub.entity.enums.CajaFormato;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de respuesta de una caja de PC")
public class CajaResponseDto {

    @Schema(description = "Identificador único", example = "1")
    private Long id;
    @Schema(description = "Nombre del modelo", example = "Fractal Design Torrent")
    private String modelo;
    @Schema(description = "ID del fabricante", example = "5")
    private Long fabricanteId;
    @Schema(description = "Precio en euros", example = "179.99")
    private BigDecimal precio;
    @Schema(description = "Factor de forma de la caja", example = "ATX")
    private CajaFormato formato;
    @Schema(description = "Formatos de placa base compatibles", example = "ATX")
    private CajaFormato placasBaseCompatibles;
    @Schema(description = "Color de la caja", example = "Negro")
    private String color;
    @Schema(description = "Dimensiones en mm (alto, ancho, profundo)")
    private Map<String, Object> dimensiones;
    @Schema(description = "Factor de forma de PSU compatible", example = "ATX")
    private PsuFactorForma psuCompatible;
    @Schema(description = "Longitud máxima de GPU en mm", example = "461")
    private Integer longitudMaxGpu;
    @Schema(description = "Número de bahías de 2.5 pulgadas", example = "2")
    private Integer bahias25;
    @Schema(description = "Número de bahías de 3.5 pulgadas", example = "2")
    private Integer bahias35;
    @Schema(description = "Espacios para ventiladores por zona")
    private Map<String, Object> espacioVentiladores;
    @Schema(description = "Indica si incluye ventiladores de fábrica", example = "true")
    private Boolean ventiladoresIncluidos;
    @Schema(description = "Soportes para radiador por zona")
    private Map<String, Object> soportesRadiador;
    @Schema(description = "Puertos en el panel frontal")
    private Map<String, Object> conectividadFrontal;
    @Schema(description = "Indica si tiene iluminación RGB", example = "false")
    private Boolean rgb;
    @Schema(description = "Altura máxima de enfriador de CPU en mm", example = "185")
    private Integer alturaMaxEnfriadorCpu;
}
