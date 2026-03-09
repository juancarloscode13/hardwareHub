package com.juanCarlos.hardwareHub.dto.response;

import com.juanCarlos.hardwareHub.entity.enums.CpuArquitectura;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpuResponseDto {

    private Long id;
    private String modelo;
    private Long fabricanteId;
    private CpuSocket cpuSocket;
    private Map<String, Object> cores;
    private Boolean cacheApilada;
    private CpuArquitectura arquitectura;
    private BigDecimal precio;
    private Integer hilos;
    private Boolean hyperthreading;
    private BigDecimal frecuenciaMax;
    private BigDecimal frecuenciaMin;
    private Integer cantidadCache;
    private Integer tdp;
    private Integer temperaturaMax;
    private Integer conectividadPcie;
    private String graficosIntegrados;
    private Integer puntuacionPassmark;
}
