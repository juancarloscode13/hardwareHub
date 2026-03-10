package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

public class GpuFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "modelo", "fabricanteId", "ensambladora", "frecuenciaMax",
            "frecuenciaMin", "temperaturaMax", "cantidadVram", "tipoVram",
            "anchoBanda", "arquitectura", "tdp", "conectividadPcie",
            "precio", "generacion", "altoGpu", "puntuacionPassmark"
    );
}
