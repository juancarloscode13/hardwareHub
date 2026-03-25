package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.RamTipo;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RamTipoConverter extends DescribedEnumConverter<RamTipo> {
    public RamTipoConverter() {
        super(RamTipo.values());
    }
}
