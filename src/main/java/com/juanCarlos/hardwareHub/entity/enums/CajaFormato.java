package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.CajaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Contiene valores normalizados sobre el formato que puede tener una caja de pc.
 *
 * @author Juan Carlos
 * @see CajaEntity
 */

@AllArgsConstructor
@Getter
public enum CajaFormato implements DescribedEnum {

    CAJA_FORMATO_MINI_ITX("Mini-ITX"),
    CAJA_FORMATO_MICRO_ATX("Micro-ATX"),
    CAJA_FORMATO_ATX("ATX"),
    CAJA_FORMATO_E_ATX("E-ATX"),
    CAJA_FORMATO_MID_TOWER("Mid Tower"),
    CAJA_FORMATO_FULL_TOWER("Full Tower"),
    CAJA_FORMATO_MINI_TOWER("Mini Tower");

    private String desc;
}