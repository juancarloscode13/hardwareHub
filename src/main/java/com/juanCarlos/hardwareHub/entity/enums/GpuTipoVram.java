package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.GpuEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Contiene valores normalizados para el tipo de Vram que puede tener una tarjeta gráfica.
 * Importante: diferenciar entre Ram y Vram.
 *
 * @author Juan Carlos
 * @see GpuEntity
 */

@AllArgsConstructor
@Getter
public enum GpuTipoVram implements DescribedEnum {

    GPU_TIPO_VRAM_GDDR6("GDDR6"),
    GPU_TIPO_VRAM_GDDR6X("GDDR6X"),
    GPU_TIPO_VRAM_GDDR7("GDDR7");

    private String desc;
}
