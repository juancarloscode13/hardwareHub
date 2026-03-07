package com.juanCarlos.hardwareHub.dto.request;

import com.juanCarlos.hardwareHub.entity.enums.RamTipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RamRequestDto {

    private String modelo;
    private Long fabricanteId;
    private BigDecimal precio;
    private Integer velocidad;
    private Integer cantidad;
    private Integer modulos;
    private Integer capacidadPorModulo;
    private RamTipo tipo;
    private Integer latencia;
}
