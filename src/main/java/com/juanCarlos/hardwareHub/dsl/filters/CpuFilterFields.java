package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

/**
 * Filtros permitidos para la entidad Cpu.
 *
 * @author Juan Carlos
 * @see com.juanCarlos.hardwareHub.entity.CpuEntity
 */
public class CpuFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "modelo", "fabricanteId", "cpuSocket", "cores",
            "cacheApilada", "arquitectura", "precio", "hilos",
            "hyperthreading", "cantidadCache", "tdp", "graficosIntegrados",
            "puntuacionPassmark"
    );
}
