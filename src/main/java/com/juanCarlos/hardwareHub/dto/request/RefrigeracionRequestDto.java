package com.juanCarlos.hardwareHub.dto.request;

import com.juanCarlos.hardwareHub.entity.enums.RefrigeracionSocketCompatible;
import com.juanCarlos.hardwareHub.entity.enums.RefrigeracionTipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefrigeracionRequestDto {

    private String modelo;
    private Long fabricanteId;
    private BigDecimal precio;
    private RefrigeracionSocketCompatible socketCompatible;
    private RefrigeracionTipo tipo;
    private Map<String, Object> atributos;
}
