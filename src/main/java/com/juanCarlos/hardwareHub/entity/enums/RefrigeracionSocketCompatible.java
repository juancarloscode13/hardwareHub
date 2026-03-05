package com.juanCarlos.hardwareHub.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RefrigeracionSocketCompatible {
    CPU_SOCKET_AM4("AM4"),
    CPU_SOCKET_AM5("AM5"),
    CPU_SOCKET_LGA1700("LGA 1700"),
    CPU_SOCKET_LGA1851("LGA 1851");

    private String desc;
}
//Aplicar Set<>