package com.juanCarlos.hardwareHub.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CajaPlacasBaseCompatibles {

    CAJA_FORMATO_MINI_ITX("Mini-ITX"),
    CAJA_FORMATO_MICRO_ATX("Micro-ATX"),
    CAJA_FORMATO_ATX("ATX"),
    CAJA_FORMATO_E_ATX("E-ATX");

    private String desc;
}
//Aplicar estrategia Set<>