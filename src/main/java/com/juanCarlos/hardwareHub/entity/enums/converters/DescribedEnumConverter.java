package com.juanCarlos.hardwareHub.entity.enums.converters;

import com.juanCarlos.hardwareHub.entity.enums.DescribedEnum;
import jakarta.persistence.AttributeConverter;

/**
 * Converter JPA genérico para enums que implementan DescribedEnum.
 * Persiste el valor de getDesc() en lugar del nombre del constante Java.
 * La comparación al leer de BD es case-insensitive para mayor robustez.
 *
 * @param <E> Enum que implementa DescribedEnum
 * @author Juan Carlos
 */
public abstract class DescribedEnumConverter<E extends Enum<E> & DescribedEnum>
        implements AttributeConverter<E, String> {

    private final E[] values;

    protected DescribedEnumConverter(E[] values) {
        this.values = values;
    }

    /** Escribe el desc del enum en la columna de BD. */
    @Override
    public String convertToDatabaseColumn(E attribute) {
        return attribute == null ? null : attribute.getDesc();
    }

    /** Lee el String de BD y lo convierte al enum correspondiente por desc (case-insensitive). */
    @Override
    public E convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        for (E value : values) {
            if (value.getDesc().equalsIgnoreCase(dbData)) return value;
        }
        throw new IllegalArgumentException(
                "No se puede convertir '" + dbData + "' al enum " +
                values[0].getClass().getSimpleName() +
                ". Valores válidos: " + buildValidValues()
        );
    }

    private String buildValidValues() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i].getDesc());
            if (i < values.length - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}

