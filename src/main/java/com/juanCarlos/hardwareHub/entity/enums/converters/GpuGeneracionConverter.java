package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.GpuGeneracion;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GpuGeneracionConverter extends DescribedEnumConverter<GpuGeneracion> {
    public GpuGeneracionConverter() {
        super(GpuGeneracion.values());
    }
}
