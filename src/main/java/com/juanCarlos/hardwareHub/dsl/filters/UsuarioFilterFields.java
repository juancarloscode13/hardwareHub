package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

/**
 * Filtros permitidos para la entidad Usuario
 *
 * @author Juan Carlos
 * @see com.juanCarlos.hardwareHub.entity.UsuarioEntity
 */
public class UsuarioFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "nombre", "email"
    );
}
