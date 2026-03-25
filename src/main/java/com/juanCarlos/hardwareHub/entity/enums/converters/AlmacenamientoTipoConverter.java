package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoTipo;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AlmacenamientoTipoConverter extends DescribedEnumConverter<AlmacenamientoTipo> {
    public AlmacenamientoTipoConverter() {
        super(AlmacenamientoTipo.values());
    }
}
