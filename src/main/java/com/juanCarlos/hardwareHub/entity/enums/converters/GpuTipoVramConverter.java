package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.GpuTipoVram;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GpuTipoVramConverter extends DescribedEnumConverter<GpuTipoVram> {
    public GpuTipoVramConverter() {
        super(GpuTipoVram.values());
    }
}
