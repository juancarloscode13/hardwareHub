package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.PlacaBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Contiene valores normalizados del posible chipset con el que puede contar una placa base.
 *
 * @author Juan Carlos
 * @see PlacaBaseEntity
 */

@AllArgsConstructor
@Getter
public enum PlacaBaseChipset implements DescribedEnum {

    PLACA_BASE_CHIPSET_X570("X570"),
    PLACA_BASE_CHIPSET_B550("B550"),
    PLACA_BASE_CHIPSET_X470("X470"),
    PLACA_BASE_CHIPSET_B450("B450"),
    PLACA_BASE_CHIPSET_X870E("X870E"),
    PLACA_BASE_CHIPSET_X870("X870"),
    PLACA_BASE_CHIPSET_B850("B850"),
    PLACA_BASE_CHIPSET_B840("B840"),
    PLACA_BASE_CHIPSET_X670E("X670E"),
    PLACA_BASE_CHIPSET_X670("X670"),
    PLACA_BASE_CHIPSET_B650E("B650E"),
    PLACA_BASE_CHIPSET_B650("B650"),
    PLACA_BASE_CHIPSET_Z790("Z790"),
    PLACA_BASE_CHIPSET_B770("B770"),
    PLACA_BASE_CHIPSET_H770("H770"),
    PLACA_BASE_CHIPSET_Z690("Z690"),
    PLACA_BASE_CHIPSET_B660("B660"),
    PLACA_BASE_CHIPSET_H610("H610"),
    PLACA_BASE_CHIPSET_Z890("Z890"),
    PLACA_BASE_CHIPSET_B860("B860"),
    PLACA_BASE_CHIPSET_H810("H810");

    private String desc;
}