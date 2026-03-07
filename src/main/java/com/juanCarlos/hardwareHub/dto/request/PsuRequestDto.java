package com.juanCarlos.hardwareHub.dto.request;

import com.juanCarlos.hardwareHub.entity.enums.PsuCertificacion;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsuRequestDto {

    private String modelo;
    private Long fabricanteId;
    private BigDecimal precio;
    private Boolean modular;
    private Integer potencia;
    private PsuCertificacion certificacion;
    private PsuFactorForma factorForma;
}
