package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.RefrigeracionEntity;
import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Contiene valores normalizados del tipo del cual puede ser una refrigeración líquida.
 *
 * @author Juan Carlos
 * @see RefrigeracionEntity
 */

@AllArgsConstructor
@Getter
public enum RefrigeracionTipo implements DescribedEnum {

    REFRIGERACION_TIPO_LIQUIDA("Liquida"),
    REFRIGERACION_TIPO_AIRE("Aire");

    private String desc;
}