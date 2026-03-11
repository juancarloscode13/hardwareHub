package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

/**
 * Filtros permitidos para la entidad PlacaBase
 *
 * @author Juan Carlos
 * @see com.juanCarlos.hardwareHub.entity.PlacaBaseEntity
 */
public class PlacaBaseFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "modelo", "fabricanteId", "precio", "socketCompatible",
            "chipset", "memoriaMaxima", "espaciosRam", "tipoRamSoportada",
            "formato", "ranurasExpansion", "ranurasAlmacenamiento", "puertosUsb",
            "wifiSoportado"
    );
}
