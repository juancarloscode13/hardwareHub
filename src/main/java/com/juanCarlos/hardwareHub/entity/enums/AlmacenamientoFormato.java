package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.AlmacenamientoEntity;
import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Contiene valores normalizados para el formato que puede tener un almacenamiento de tipo SSD o HDD.
 *
 * @author Juan Carlos
 * @see AlmacenamientoEntity
 */

@AllArgsConstructor
@Getter
public enum AlmacenamientoFormato {

    ALMACENAMIENTO_FORMATO_2_5("2.5"),
    ALMACENAMIENTO_FORMATO_3_5("3.5");

    private String desc;
}