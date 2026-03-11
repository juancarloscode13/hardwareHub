package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

/**
 * Filtros permitidos para la entidad Comentario
 *
 * @author Juan Carlos
 * @see com.juanCarlos.hardwareHub.entity.ComentarioEntity
 */
public class ComentarioFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "contenido", "fecha", "usuarioId", "publicacionId", "comentarioId"
    );
}
