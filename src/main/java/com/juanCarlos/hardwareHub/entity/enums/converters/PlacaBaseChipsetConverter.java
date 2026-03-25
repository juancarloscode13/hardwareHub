package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseChipset;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PlacaBaseChipsetConverter extends DescribedEnumConverter<PlacaBaseChipset> {
    public PlacaBaseChipsetConverter() {
        super(PlacaBaseChipset.values());
    }
}
