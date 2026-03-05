package com.juanCarlos.hardwareHub.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GpuEnsambladora {

    GPU_ENSAMBLADORA_ACER("Acer"),
    GPU_ENSAMBLADORA_ASROCK("Asrock"),
    GPU_ENSAMBLADORA_ASUS("Asus"),
    GPU_ENSAMBLADORA_BIOSTAR("Biostar"),
    GPU_ENSAMBLADORA_GAINWARD("Gainward"),
    GPU_ENSAMBLADORA_GIGABYTE("Gigabyte"),
    GPU_ENSAMBLADORA_INNO3D("Inno3D"),
    GPU_ENSAMBLADORA_INTEL("Intel"),
    GPU_ENSAMBLADORA_NVIDIA("Nvidia"),
    GPU_ENSAMBLADORA_LENOVO("Lenovo"),
    GPU_ENSAMBLADORA_MSI("MSI"),
    GPU_ENSAMBLADORA_PALIT("Palit"),
    GPU_ENSAMBLADORA_PNY("PNY"),
    GPU_ENSAMBLADORA_POWERCOLOR("PowerColor"),
    GPU_ENSAMBLADORA_SAPPHIRE("Sapphire"),
    GPU_ENSAMBLADORA_SPARKLE("Sparkle"),
    GPU_ENSAMBLADORA_XFX("XFX"),
    GPU_ENSAMBLADORA_ZOTAC("Zotac");

    private String desc;
}
