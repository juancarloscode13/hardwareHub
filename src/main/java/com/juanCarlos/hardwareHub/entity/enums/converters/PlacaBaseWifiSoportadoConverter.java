package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseWifiSoportado;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PlacaBaseWifiSoportadoConverter extends DescribedEnumConverter<PlacaBaseWifiSoportado> {
    public PlacaBaseWifiSoportadoConverter() {
        super(PlacaBaseWifiSoportado.values());
    }
}
