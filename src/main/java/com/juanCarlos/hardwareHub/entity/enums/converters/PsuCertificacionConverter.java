package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.PsuCertificacion;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PsuCertificacionConverter extends DescribedEnumConverter<PsuCertificacion> {
    public PsuCertificacionConverter() {
        super(PsuCertificacion.values());
    }
}
