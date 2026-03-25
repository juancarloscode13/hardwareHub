package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.GpuEnsambladora;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GpuEnsambladoraConverter extends DescribedEnumConverter<GpuEnsambladora> {
    public GpuEnsambladoraConverter() {
        super(GpuEnsambladora.values());
    }
}
