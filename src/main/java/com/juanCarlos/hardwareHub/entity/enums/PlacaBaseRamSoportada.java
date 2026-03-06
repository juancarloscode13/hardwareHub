package com.juanCarlos.hardwareHub.entity.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public enum PlacaBaseRamSoportada {

    RAM_TIPO_DDR4("DDR4"),
    RAM_TIPO_DDR5("DDR5");

    private String desc;
}