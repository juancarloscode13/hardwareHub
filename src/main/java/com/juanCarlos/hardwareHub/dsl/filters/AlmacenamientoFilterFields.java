package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

/**
 * Filtros permitidos para la entidad Almacenamiento.
 *
 * @author Juan Carlos
 * @see com.juanCarlos.hardwareHub.entity.AlmacenamientoEntity
 */
public class AlmacenamientoFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "modelo", "fabricanteId", "precio", "capacidad",
            "tipo", "formato", "velocidadLectura", "velocidadEscritura", "conectividad"
    );
}
