package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.GpuArquitectura;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GpuArquitecturaConverter extends DescribedEnumConverter<GpuArquitectura> {
    public GpuArquitecturaConverter() {
        super(GpuArquitectura.values());
    }
}
