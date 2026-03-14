package com.juanCarlos.hardwareHub.dto.request;

import com.juanCarlos.hardwareHub.entity.enums.GpuArquitectura;
import com.juanCarlos.hardwareHub.entity.enums.GpuEnsambladora;
import com.juanCarlos.hardwareHub.entity.enums.GpuGeneracion;
import com.juanCarlos.hardwareHub.entity.enums.GpuTipoVram;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de entrada para crear o actualizar una GPU")
public class GpuRequestDto {

    @Schema(description = "Nombre del modelo", example = "RTX 5090")
    private String modelo;
    @Schema(description = "ID del fabricante (chip)", example = "2")
    private Long fabricanteId;
    @Schema(description = "Ensambladora / AIB partner", example = "ASUS")
    private GpuEnsambladora ensambladora;
    @Schema(description = "Distribución de núcleos shaders / RT / Tensor")
    private Map<String, Object> nucleos;
    @Schema(description = "Frecuencia boost en MHz", example = "2400.0")
    private BigDecimal frecuenciaMax;
    @Schema(description = "Frecuencia base en MHz", example = "2100.0")
    private BigDecimal frecuenciaMin;
    @Schema(description = "Temperatura máxima de operación en °C", example = "90")
    private Integer temperaturaMax;
    @Schema(description = "Cantidad de VRAM en GB", example = "32")
    private Integer cantidadVram;
    @Schema(description = "Tipo de memoria VRAM", example = "GDDR7")
    private GpuTipoVram tipoVram;
    @Schema(description = "Ancho de banda de memoria en GB/s", example = "1792")
    private Integer anchoBanda;
    @Schema(description = "Arquitectura de la GPU", example = "BLACKWELL")
    private GpuArquitectura arquitectura;
    @Schema(description = "TDP en vatios", example = "575")
    private Integer tdp;
    @Schema(description = "Versión del bus PCIe", example = "5")
    private Integer conectividadPcie;
    @Schema(description = "Precio en euros", example = "1999.99")
    private BigDecimal precio;
    @Schema(description = "Generación de la GPU")
    private GpuGeneracion generacion;
    @Schema(description = "Alto de la tarjeta en mm (slots ocupados)", example = "336")
    private Integer altoGpu;
    @Schema(description = "Puntuación en PassMark G3D benchmark", example = "40000")
    private Integer puntuacionPassmark;
}
