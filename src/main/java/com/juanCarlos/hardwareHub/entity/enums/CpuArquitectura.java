package com.juanCarlos.hardwareHub.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CpuArquitectura {

    CPU_ARQUITECTURA_ZEN3("ZEN 3"),
    CPU_ARQUITECTURA_ZEN4("ZEN 4"),
    CPU_ARQUITECTURA_ZEN5("ZEN 5"),
    CPU_ARQUITECTURA_ALDER_LAKE("Alder Lake"),
    CPU_ARQUITECTURA_RAPTOR_LAKE("Raptor Lake"),
    CPU_ARQUITECTURA_RAPTOR_LAKE_REFRESH("Raptor Lake Refresh"),
    CPU_ARQUITECTURA_ARROW_LAKE("Arrow Lake");

    private String desc;
}
