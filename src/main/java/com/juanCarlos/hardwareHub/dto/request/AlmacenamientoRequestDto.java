package com.juanCarlos.hardwareHub.dto.request;

import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoConectividad;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoFormato;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoTipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlmacenamientoRequestDto {

    private String modelo;
    private Long fabricanteId;
    private BigDecimal precio;
    private BigDecimal capacidad;
    private AlmacenamientoTipo tipo;
    private AlmacenamientoFormato formato;
    private Integer velocidadLectura;
    private Integer velocidadEscritura;
    private AlmacenamientoConectividad conectividad;
}
