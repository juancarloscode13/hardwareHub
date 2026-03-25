package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoConectividad;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AlmacenamientoConectividadConverter extends DescribedEnumConverter<AlmacenamientoConectividad> {
    public AlmacenamientoConectividadConverter() {
        super(AlmacenamientoConectividad.values());
    }
}
