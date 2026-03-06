package com.juanCarlos.hardwareHub.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlacaBaseFormato {

    PLACA_BASE_FORMATO_MINI_ITX("Mini-ITX"),
    PLACA_BASE_FORMATO_MICRO_ATX("Micro-ATX"),
    PLACA_BASE_FORMATO_ATX("ATX"),
    PLACA_BASE_FORMATO_E_ATX("E-ATX");

    private String desc;
}