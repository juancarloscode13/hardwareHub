package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.PlacaBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Contiene valores normalizados del formato con el que puede contar una placa base.
 *
 * @author Juan Carlos
 * @see PlacaBaseEntity
 */

@AllArgsConstructor
@Getter
public enum PlacaBaseFormato {

    PLACA_BASE_FORMATO_MINI_ITX("Mini-ITX"),
    PLACA_BASE_FORMATO_MICRO_ATX("Micro-ATX"),
    PLACA_BASE_FORMATO_ATX("ATX"),
    PLACA_BASE_FORMATO_E_ATX("E-ATX");

    private String desc;
}