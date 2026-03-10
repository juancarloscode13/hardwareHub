package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

public class PsuFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "modelo", "fabricanteId", "precio", "modular",
            "potencia", "certificacion", "factorForma"
    );
}
