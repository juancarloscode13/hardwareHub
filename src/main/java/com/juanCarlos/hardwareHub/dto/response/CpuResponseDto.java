package com.juanCarlos.hardwareHub.dto.response;

import com.juanCarlos.hardwareHub.entity.enums.CpuArquitectura;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de respuesta de una CPU")
public class CpuResponseDto {

    @Schema(description = "Identificador único", example = "1")
    private Long id;
    @Schema(description = "Nombre del modelo", example = "Ryzen 9 9950X")
    private String modelo;
    @Schema(description = "ID del fabricante", example = "1")
    private Long fabricanteId;
    @Schema(description = "Socket del procesador", example = "AM5")
    private CpuSocket cpuSocket;
    @Schema(description = "Distribución de núcleos (P-cores / E-cores)")
    private Map<String, Object> cores;
    @Schema(description = "Indica si la CPU tiene caché 3D apilada", example = "false")
    private Boolean cacheApilada;
    @Schema(description = "Arquitectura del procesador", example = "ZEN5")
    private CpuArquitectura arquitectura;
    @Schema(description = "Precio en euros", example = "599.99")
    private BigDecimal precio;
    @Schema(description = "Número total de hilos", example = "32")
    private Integer hilos;
    @Schema(description = "Soporte de hyperthreading / SMT", example = "true")
    private Boolean hyperthreading;
    @Schema(description = "Frecuencia máxima en GHz", example = "5.70")
    private BigDecimal frecuenciaMax;
    @Schema(description = "Frecuencia base en GHz", example = "4.30")
    private BigDecimal frecuenciaMin;
    @Schema(description = "Cantidad de caché L3 en MB", example = "64")
    private Integer cantidadCache;
    @Schema(description = "TDP en vatios", example = "170")
    private Integer tdp;
    @Schema(description = "Temperatura máxima de operación en °C", example = "95")
    private Integer temperaturaMax;
    @Schema(description = "Versión del bus PCIe soportado", example = "5")
    private Integer conectividadPcie;
    @Schema(description = "Nombre de la GPU integrada (null si no tiene)", example = "Radeon 890M")
    private String graficosIntegrados;
    @Schema(description = "Puntuación en PassMark benchmark", example = "70000")
    private Integer puntuacionPassmark;
}
