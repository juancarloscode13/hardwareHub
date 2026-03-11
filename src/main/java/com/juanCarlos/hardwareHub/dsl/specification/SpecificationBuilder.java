package com.juanCarlos.hardwareHub.dsl.specification;

import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Combina todos los filtros en una sola consulta de base de datos. Recibe la lista de filtros individuales y los va
 * uniendo con AND, de forma que el resultado final cumple todas las condiciones a la vez.
 *
 * @author Juan Carlos
 * @see FilterCriteria
 * @see GenericSpecification
 */
public class SpecificationBuilder<T> {

    public Specification<T> build(List<FilterCriteria> filters) {

        Specification<T> spec = Specification.where((Specification<T>) null);

        for (FilterCriteria filter : filters) {
            spec = spec.and(new GenericSpecification<>(filter));
        }
        return spec;
    }
}
