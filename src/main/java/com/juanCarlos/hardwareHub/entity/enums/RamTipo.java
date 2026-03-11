package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.RamEntity;
import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Contiene valores normalizados de el tipo del que puede ser una memoria ram.
 *
 * @author Juan Carlos
 * @see RamEntity
 */

@AllArgsConstructor
@Getter
public enum RamTipo {

    RAM_TIPO_DDR4("DDR4"),
    RAM_TIPO_DDR5("DDR5");

    private String desc;
}