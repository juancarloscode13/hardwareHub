package com.juanCarlos.hardwareHub.entity.enums;

/**
 * Interfaz marcadora para enums que tienen un campo desc
 * utilizado como valor persistido en base de datos.
 * Los AttributeConverter usan getDesc() para la conversión DB ↔ Java.
 *
 * @author Juan Carlos
 */
public interface DescribedEnum {
    String getDesc();
}

