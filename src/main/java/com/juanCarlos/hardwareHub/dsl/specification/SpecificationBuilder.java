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

    /**
     * Combina una lista de filtros individuales en una sola {@link Specification}
     * usando AND entre cada condición.
     *
     * @param filters lista de filtros parseados
     * @return Specification que representa la conjunción de todos los filtros
     */
    public Specification<T> build(List<FilterCriteria> filters) {

        Specification<T> spec = (_, _, criteriaBuilder) -> criteriaBuilder.conjunction();

        for (FilterCriteria filter : filters) {
            spec = spec.and(new GenericSpecification<>(filter));
        }
        return spec;
    }
}
