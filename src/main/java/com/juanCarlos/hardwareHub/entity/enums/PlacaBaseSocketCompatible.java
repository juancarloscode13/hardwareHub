package com.juanCarlos.hardwareHub.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlacaBaseSocketCompatible {

    PLACA_BASE_SOCKET_COMPATIBLE_AM4("AM4"),
    PLACA_BASE_SOCKET_COMPATIBLE_AM5("AM5"),
    PLACA_BASE_SOCKET_COMPATIBLE_LGA_1700("LGA-1700"),
    PLACA_BASE_SOCKET_COMPATIBLE_LGA_1851("LGA-1851");

    private String desc;
}