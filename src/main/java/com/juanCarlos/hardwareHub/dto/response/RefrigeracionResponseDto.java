package com.juanCarlos.hardwareHub.dto.response;

import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.entity.enums.RefrigeracionTipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefrigeracionResponseDto {

    private Long id;
    private String modelo;
    private Long fabricanteId;
    private BigDecimal precio;
    private CpuSocket socketCompatible;
    private RefrigeracionTipo tipo;
    private Map<String, Object> atributos;
}
