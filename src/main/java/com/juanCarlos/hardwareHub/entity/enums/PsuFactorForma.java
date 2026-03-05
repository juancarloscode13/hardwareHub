package com.juanCarlos.hardwareHub.entity.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public enum PsuFactorForma {

    PSU_FACTOR_FORMA_ATX("ATX"),
    PSU_FACTOR_FORMA_SFX("SFX"),
    PSU_FACTOR_FORMA_SFX_L("SFX-L"),
    PSU_FACTOR_FORMA_TFX("TFX");

    private String desc;
}