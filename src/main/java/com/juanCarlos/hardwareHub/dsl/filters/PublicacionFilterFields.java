package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

/**
 * Filtros permitidos para la entidad Publicacion.
 *
 * @author Juan Carlos
 * @see com.juanCarlos.hardwareHub.entity.PublicacionEntity
 */
public class PublicacionFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "contenidoTexto", "fecha", "usuarioId", "montajeId", "usuario", "montaje"
    );
}
