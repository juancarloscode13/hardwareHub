package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.PsuEntity;
import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Contiene valores normalizados sobre el factor de forma que puede tener una fuente de alimentación.
 *
 * @author Juan Carlos
 * @see PsuEntity
 */

@AllArgsConstructor
@Getter
public enum PsuFactorForma implements DescribedEnum {

    PSU_FACTOR_FORMA_ATX("ATX"),
    PSU_FACTOR_FORMA_SFX("SFX"),
    PSU_FACTOR_FORMA_SFX_L("SFX-L"),
    PSU_FACTOR_FORMA_TFX("TFX");

    private String desc;
}