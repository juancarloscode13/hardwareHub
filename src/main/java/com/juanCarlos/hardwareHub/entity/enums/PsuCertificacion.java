package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.PsuEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Contiene valores normalizados sobre la certificación que puede tener una fuente de alimentación.
 *
 * @author Juan Carlos
 * @see PsuEntity
 */

@AllArgsConstructor
@Getter
public enum PsuCertificacion {

    PSU_CERTIFICACION_80_PLUS_WHITE("80 PLUS White"),
    PSU_CERTIFICACION_80_PLUS_BRONZE("80 PLUS Bronze"),
    PSU_CERTIFICACION_80_PLUS_SILVER("80 PLUS Silver"),
    PSU_CERTIFICACION_80_PLUS_GOLD("80 PLUS Gold"),
    PSU_CERTIFICACION_80_PLUS_PLATINUM("80 PLUS Platinum"),
    PSU_CERTIFICACION_80_PLUS_TITANIUM("80 PLUS Titanium");

    private String desc;
}