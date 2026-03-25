package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.CajaFormato;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CajaFormatoConverter extends DescribedEnumConverter<CajaFormato> {
    public CajaFormatoConverter() {
        super(CajaFormato.values());
    }
}
