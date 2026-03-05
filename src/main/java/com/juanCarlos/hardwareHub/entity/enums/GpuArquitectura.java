package com.juanCarlos.hardwareHub.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GpuArquitectura {

    GPU_ARQUITECTURA_ADA_LOVELACE("Ada Lovelace"),
    GPU_ARQUITECTURA_BLACKWELL("Blackwell"),
    GPU_ARQUITECTURA_RDNA3("RDNA3"),
    GPU_ARQUITECTURA_RDNA4("RDNA4"),
    GPU_ARQUITECTURA_BATTLEMAGE("Battlemage"),
    GPU_ARQUITECTURA_ALCHEMIST("Alchemist");

    private String desc;
}
