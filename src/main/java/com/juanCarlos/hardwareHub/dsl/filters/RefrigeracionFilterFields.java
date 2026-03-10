package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

public class RefrigeracionFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "modelo", "fabricanteId", "socketCompatible", "tipo",
            "precio", "tdp", "altura", "ventiladores"
    );
}
