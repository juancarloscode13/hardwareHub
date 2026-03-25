package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.RefrigeracionTipo;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RefrigeracionTipoConverter extends DescribedEnumConverter<RefrigeracionTipo> {
    public RefrigeracionTipoConverter() {
        super(RefrigeracionTipo.values());
    }
}
