package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

/**
 * Filtros permitidos para la entidad Montaje
 *
 * @author Juan Carlos
 * @see com.juanCarlos.hardwareHub.entity.MontajeEntity
 */
public class MontajeFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "nombre", "usuarioId", "descripcion", "precioTotal"
    );
}
