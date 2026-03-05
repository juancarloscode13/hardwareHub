package com.juanCarlos.hardwareHub.entity.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public enum RefrigeracionTipo {

    REFRIGERACION_TIPO_LIQUIDA("Líquida"),
    REFRIGERACION_TIPO_AIRE("Aire");

    private String desc;
}