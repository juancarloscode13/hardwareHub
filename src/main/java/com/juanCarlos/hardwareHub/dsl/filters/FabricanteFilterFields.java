package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

/**
 * Campos permitidos para la entidad Fabricante
 *
 * @author Juan Carlos
 * @see com.juanCarlos.hardwareHub.entity.FabricanteEntity
 */
public class FabricanteFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "nombre"
    );
}
