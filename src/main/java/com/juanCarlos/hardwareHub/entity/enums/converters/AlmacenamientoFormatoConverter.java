package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoFormato;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AlmacenamientoFormatoConverter extends DescribedEnumConverter<AlmacenamientoFormato> {
    public AlmacenamientoFormatoConverter() {
        super(AlmacenamientoFormato.values());
    }
}
