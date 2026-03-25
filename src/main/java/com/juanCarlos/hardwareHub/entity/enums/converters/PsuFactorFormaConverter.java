package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PsuFactorFormaConverter extends DescribedEnumConverter<PsuFactorForma> {
    public PsuFactorFormaConverter() {
        super(PsuFactorForma.values());
    }
}
