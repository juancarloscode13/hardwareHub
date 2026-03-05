package com.juanCarlos.hardwareHub.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GpuTipoVram {

    GPU_TIPO_VRAM_GDDR6("GDDR6"),
    GPU_TIPO_VRAM_GDDR6X("GDDR6X"),
    GPU_TIPO_VRAM_GDDR7("GDDR7");

    private String desc;
}
