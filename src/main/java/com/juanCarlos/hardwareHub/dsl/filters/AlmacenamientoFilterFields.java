package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

public class AlmacenamientoFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "modelo", "fabricanteId", "precio", "capacidad",
            "tipo", "formato", "velocidadLectura", "velocidadEscritura", "conectividad"
    );
}
