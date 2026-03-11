package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

/**
 * Filtros permitidos para la entidad Psu
 *
 * @author Juan Carlos
 * @see com.juanCarlos.hardwareHub.entity.PsuEntity
 */
public class PsuFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "modelo", "fabricanteId", "precio", "modular",
            "potencia", "certificacion", "factorForma"
    );
}
