package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

public class ComentarioFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "contenido", "fecha", "usuarioId", "publicacionId", "comentarioId"
    );
}
