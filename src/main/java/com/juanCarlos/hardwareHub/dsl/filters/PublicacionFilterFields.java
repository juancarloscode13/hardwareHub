package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

public class PublicacionFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "titulo", "contenido", "fecha", "usuarioId", "montajeId"
    );
}
