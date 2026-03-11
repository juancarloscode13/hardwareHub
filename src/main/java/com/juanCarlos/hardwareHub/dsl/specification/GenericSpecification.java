package com.juanCarlos.hardwareHub.dsl.specification;

import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Traduce un filtro individual (FilterCriteria) a una condición real de base de datos. Según el operador que tenga el
 * filtro (==, ~, >, <, >=, <=), genera la consulta correspondiente.
 *
 * @author Juan Carlos
 * @see FilterCriteria
 */
@AllArgsConstructor
public class GenericSpecification<T> implements Specification<T> {

    private FilterCriteria criteria;

    @Override
    public @Nullable Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (criteria.getOperator()) {
            case "==":
                return criteriaBuilder.equal(root.get(criteria.getField()), criteria.getValue());

            case "~":
                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(criteria.getField())),
                        "%" + criteria.getValue().toLowerCase() + "%");

            case ">":
                return criteriaBuilder.greaterThan(root.get(criteria.getField()), criteria.getValue());

            case "<":
                return criteriaBuilder.lessThan(root.get(criteria.getField()), criteria.getValue());

            case "<=":
                return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getField()), criteria.getValue());

            case ">=":
                return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getField()), criteria.getValue());

            default:
                return null;
        }
    }
}
