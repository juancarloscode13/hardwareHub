package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.CpuArquitectura;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CpuArquitecturaConverter extends DescribedEnumConverter<CpuArquitectura> {
    public CpuArquitecturaConverter() {
        super(CpuArquitectura.values());
    }
}
