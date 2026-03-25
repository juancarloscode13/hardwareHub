package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.GpuEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Contiene valores normalizados sobre la arquitectura que puede tener una tarjeta gráfica.
 *
 * @author Juan Carlos
 * @see GpuEntity
 */

@AllArgsConstructor
@Getter
public enum GpuArquitectura implements DescribedEnum {

    GPU_ARQUITECTURA_ADA_LOVELACE("Ada Lovelace"),
    GPU_ARQUITECTURA_BLACKWELL("Blackwell"),
    GPU_ARQUITECTURA_RDNA3("RDNA 3"),
    GPU_ARQUITECTURA_RDNA4("RDNA 4"),
    GPU_ARQUITECTURA_BATTLEMAGE("Battlemage"),
    GPU_ARQUITECTURA_ALCHEMIST("Alchemist");

    private String desc;
}
