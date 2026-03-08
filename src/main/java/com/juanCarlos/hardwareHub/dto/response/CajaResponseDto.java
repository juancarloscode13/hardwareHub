package com.juanCarlos.hardwareHub.dto.response;

import com.juanCarlos.hardwareHub.entity.enums.CajaFormato;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CajaResponseDto {

    private Long id;
    private String modelo;
    private Long fabricanteId;
    private BigDecimal precio;
    private CajaFormato formato;
    private CajaFormato placasBaseCompatibles;
    private String color;
    private Map<String, Object> dimensiones;
    private PsuFactorForma psuCompatible;
    private Map<String, Object> espacioMaxGpu;
    private Integer bahias25;
    private Integer bahias35;
    private Map<String, Object> espacioVentiladores;
    private Boolean ventiladoresIncluidos;
    private Map<String, Object> soportesRadiador;
    private Map<String, Object> conectividadFrontal;
    private Boolean rgb;
    private Integer alturaMaxEnfriadorCpu;
}
