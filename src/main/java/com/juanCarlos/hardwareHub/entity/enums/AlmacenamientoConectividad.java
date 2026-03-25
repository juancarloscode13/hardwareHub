package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.AlmacenamientoEntity;
import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Contiene valores normalizados para el tipo de conectividad que puede tener un almacenamiento.
 *
 * @author Juan Carlos
 * @see AlmacenamientoEntity
 */

@AllArgsConstructor
@Getter
public enum AlmacenamientoConectividad implements DescribedEnum {

    ALMACENAMIENTO_CONECTIVIDAD_PCIE("PCIe 4.0 x4"),
    ALMACENAMIENTO_CONECTIVIDAD_SATA("SATA III");

    private String desc;
}