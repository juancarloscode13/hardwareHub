package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CpuSocketConverter extends DescribedEnumConverter<CpuSocket> {
    public CpuSocketConverter() {
        super(CpuSocket.values());
    }
}
