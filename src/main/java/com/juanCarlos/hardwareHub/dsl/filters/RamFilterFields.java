package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

public class RamFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "modelo", "fabricanteId", "precio", "velocidad",
            "cantidad", "modulos", "capacidadPorModulo", "tipo", "latencia"
    );
}
