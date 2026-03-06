package com.juanCarlos.hardwareHub.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CajaTipoPsuCompatible {

    CAJA_TIPO_PSU_COMPATIBLE_ATX("ATX"),
    CAJA_TIPO_PSU_COMPATIBLE_SFX("SFX"),
    CAJA_TIPO_PSU_COMPATIBLE_SFX_L("SFX-L"),
    CAJA_TIPO_PSU_COMPATIBLE_TFX("TFX");

    private String desc;
}
