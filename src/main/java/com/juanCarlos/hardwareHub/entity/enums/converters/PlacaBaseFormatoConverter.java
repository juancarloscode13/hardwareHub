package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseFormato;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PlacaBaseFormatoConverter extends DescribedEnumConverter<PlacaBaseFormato> {
    public PlacaBaseFormatoConverter() {
        super(PlacaBaseFormato.values());
    }
}
