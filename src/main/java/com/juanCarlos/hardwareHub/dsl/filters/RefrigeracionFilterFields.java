package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

/**
 * Filtros permitidos para la entidad Refrigeracion
 *
 * @author Juan Carlos
 * @see com.juanCarlos.hardwareHub.entity.RefrigeracionEntity
 */
public class RefrigeracionFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "modelo", "fabricanteId", "socketCompatible", "tipo",
            "precio", "tdp", "altura", "ventiladores"
    );
}
