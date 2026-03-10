package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

public class CajaFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "modelo", "fabricanteId", "precio", "formato",
            "placasBaseCompatibles", "color", "psuCompatible", "bahias25",
            "bahias35", "ventiladoresIncluidos", "alturaMaxEnfriadorCpu"
    );
}
