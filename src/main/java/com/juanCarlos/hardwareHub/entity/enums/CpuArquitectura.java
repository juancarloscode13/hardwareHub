package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.CpuEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Contiene valores normalizados sobre la arquitectura que puede tener una cpu.
 *
 * @author Juan Carlos
 * @see CpuEntity
 */

@AllArgsConstructor
@Getter
public enum CpuArquitectura implements DescribedEnum {

    CPU_ARQUITECTURA_ZEN3("Zen 3"),
    CPU_ARQUITECTURA_ZEN4("Zen 4"),
    CPU_ARQUITECTURA_ZEN5("Zen 5"),
    CPU_ARQUITECTURA_ALDER_LAKE("Alder Lake"),
    CPU_ARQUITECTURA_RAPTOR_LAKE("Raptor Lake"),
    CPU_ARQUITECTURA_RAPTOR_LAKE_REFRESH("Raptor Lake Refresh"),
    CPU_ARQUITECTURA_ARROW_LAKE("Arrow Lake");

    private String desc;
}
