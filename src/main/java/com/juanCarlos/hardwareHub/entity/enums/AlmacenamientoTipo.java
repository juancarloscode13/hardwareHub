package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.AlmacenamientoEntity;
import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Contiene valores normalizados sobre el tipo del que puede ser un almacenamiento.
 *
 * @author Juan Carlos
 * @see AlmacenamientoEntity
 */

@AllArgsConstructor
@Getter
public enum AlmacenamientoTipo implements DescribedEnum {

    ALMACENAMIENTO_TIPO_NVME_SSD("NVMe SSD"),
    ALMACENAMIENTO_TIPO_HDD("HDD"),
    ALMACENAMIENTO_TIPO_SATA_SSD("SATA SSD");

    private String desc;
}