package com.juanCarlos.hardwareHub.dsl.validation;

import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import com.juanCarlos.hardwareHub.exception.ForbiddenFieldException;

import java.util.List;
import java.util.Set;

/**
 * Comprueba que los filtros que ha enviado el usuario solo usen campos permitidos. Si alguien intenta filtrar por un
 * campo que no está en la lista de campos válidos, lanza una excepción para evitar accesos no autorizados a datos.
 *
 * @author Juan Carlos
 */
public class FilterValidator {

    public static void validate(List<FilterCriteria> filters, Set<String> allowedFields) {
        if (filters == null || filters.isEmpty())
            return;

        for (FilterCriteria filter : filters) {
            if (!allowedFields.contains(filter.getField())) {
                throw new ForbiddenFieldException();
            }
        }
    }
}
