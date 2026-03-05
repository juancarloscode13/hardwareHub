package com.juanCarlos.hardwareHub.entity.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public enum AlmacenamientoConectividad {

    ALMACENAMIENTO_CONECTIVIDAD_PCIE("PCIE"),
    ALMACENAMIENTO_CONECTIVIDAD_SATA("SATA");

    private String desc;
}