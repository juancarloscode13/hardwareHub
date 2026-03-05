package com.juanCarlos.hardwareHub.entity.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public enum AlmacenamientoFormato {

    ALMACENAMIENTO_FORMATO_2_5("2.5"),
    ALMACENAMIENTO_FORMATO_3_5("3.5");

    private String desc;
}