package com.juanCarlos.hardwareHub.dto.request;

import com.juanCarlos.hardwareHub.entity.enums.GpuArquitectura;
import com.juanCarlos.hardwareHub.entity.enums.GpuEnsambladora;
import com.juanCarlos.hardwareHub.entity.enums.GpuGeneracion;
import com.juanCarlos.hardwareHub.entity.enums.GpuTipoVram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GpuRequestDto {

    private String modelo;
    private Long fabricanteId;
    private GpuEnsambladora ensambladora;
    private Map<String, Object> nucleos;
    private BigDecimal frecuenciaMax;
    private BigDecimal frecuenciaMin;
    private Integer temperaturaMax;
    private Integer cantidadVram;
    private GpuTipoVram tipoVram;
    private Integer anchoBanda;
    private GpuArquitectura arquitectura;
    private Integer tdp;
    private Integer conectividadPcie;
    private BigDecimal precio;
    private GpuGeneracion generacion;
    private Integer altoGpu;
    private Integer puntuacionPassmark;
}
