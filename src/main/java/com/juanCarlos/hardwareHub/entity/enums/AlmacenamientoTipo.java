package com.juanCarlos.hardwareHub.entity.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public enum AlmacenamientoTipo {

    ALMACENAMIENTO_TIPO_NVME_M2("NVME M.2"),
    ALMACENAMIENTO_TIPO_HDD("HDD"),
    ALMACENAMIENTO_TIPO_SSD("SSD");

    private String desc;
}