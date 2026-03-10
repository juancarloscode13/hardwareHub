package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

public class UsuarioFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "nombre", "email"
    );
}
