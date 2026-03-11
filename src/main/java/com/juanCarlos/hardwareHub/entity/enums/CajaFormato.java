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
public enum CajaFormato {

    CAJA_FORMATO_MINI_ITX("Mini-ITX"),
    CAJA_FORMATO_MICRO_ATX("Micro-ATX"),
    CAJA_FORMATO_ATX("ATX"),
    CAJA_FORMATO_E_ATX("E-ATX");

    private String desc;
}