package com.juanCarlos.hardwareHub.dsl.filters;

import java.util.Set;

public class PlacaBaseFilterFields {
    public static final Set<String> ALLOWED_FIELDS = Set.of(
            "id", "modelo", "fabricanteId", "precio", "socketCompatible",
            "chipset", "memoriaMaxima", "espaciosRam", "tipoRamSoportada",
            "formato", "ranurasExpansion", "ranurasAlmacenamiento", "puertosUsb",
            "wifiSoportado"
    );
}
