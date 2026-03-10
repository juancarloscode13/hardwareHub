package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

public class CpuFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "modelo", "fabricanteId", "cpuSocket", "cores",
            "cacheApilada", "arquitectura", "precio", "hilos",
            "hyperthreading", "cantidadCache", "tdp", "graficosIntegrados",
            "puntuacionPassmark"
    );
}
