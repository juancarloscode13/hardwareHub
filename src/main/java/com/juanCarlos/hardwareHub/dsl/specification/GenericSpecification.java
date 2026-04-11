package com.juanCarlos.hardwareHub.dsl.specification;

import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

/**
 * Traduce un filtro individual (FilterCriteria) a una condición real de base de datos. Según el operador que tenga el
 * filtro (==, ~, >, <, >=, <=), genera la consulta correspondiente.
 * <p>
 * Convierte automáticamente el valor String de la URL al tipo Java real del campo (Boolean, Integer, Long,
 * BigDecimal, Double, Enum), evitando el SemanticException de Hibernate 6.x por incompatibilidad de tipos.
 *
 * @author Juan Carlos
 * @see FilterCriteria
 */
@AllArgsConstructor
public class GenericSpecification<T> implements Specification<T> {

    private FilterCriteria criteria;

    @Override
    /**
     * Convierte el {@link FilterCriteria} de esta especificación a un {@link Predicate}
     * de JPA Criteria según el operador.
     *
     * @param root raíz del query
     * @param query consulta Criteria
     * @param criteriaBuilder builder para construir predicados
     * @return Predicate correspondiente al filtro o null si el operador no es conocido
     */
    public @Nullable Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Path<?> path = resolvePath(root, criteria.getField());

        return switch (criteria.getOperator()) {
            case "==" -> criteriaBuilder.equal(path, convertValue(path.getJavaType(), criteria.getValue()));
            case "!=" -> criteriaBuilder.notEqual(path, convertValue(path.getJavaType(), criteria.getValue()));
            case "~"  -> criteriaBuilder.like(
                    criteriaBuilder.lower(path.as(String.class)),
                    "%" + criteria.getValue().toLowerCase() + "%");
            case ">", "<", ">=", "<=" -> buildComparisonPredicate(criteriaBuilder, path, criteria.getOperator(), criteria.getValue());
            default -> null;
        };
    }

    /**
     * Resuelve el path real del filtro.
     * Casos soportados:
     * - campo simple: "fecha"
     * - campo anidado: "usuario.id"
     * - alias relacional por id: "usuarioId" -> "usuario.id"
     */
    private Path<?> resolvePath(Root<T> root, String field) {
        // Path anidado explícito: usuario.id
        if (field.contains(".")) {
            String[] parts = field.split("\\.");
            Path<?> path = root.get(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                path = path.get(parts[i]);
            }
            return path;
        }

        // Campo directo de la entidad
        try {
            return root.get(field);
        } catch (IllegalArgumentException ignored) {
            // Alias común de FK: usuarioId -> usuario.id
            if (field.endsWith("Id") && field.length() > 2) {
                String relation = field.substring(0, field.length() - 2);
                return root.get(relation).get("id");
            }
            throw ignored;
        }
    }

    /**
     * Convierte el valor String recibido desde la URL al tipo Java real del campo JPA.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private Object convertValue(Class<?> javaType, String value) {
        if (javaType == Boolean.class || javaType == boolean.class) return Boolean.parseBoolean(value);
        if (javaType == Integer.class || javaType == int.class)     return Integer.parseInt(value);
        if (javaType == Long.class    || javaType == long.class)    return Long.parseLong(value);
        if (javaType == Double.class  || javaType == double.class)  return Double.parseDouble(value);
        if (javaType == BigDecimal.class)                           return new BigDecimal(value);
        if (javaType.isEnum())                                      return Enum.valueOf((Class<Enum>) javaType, value);
        return value;
    }

    /**
     * Construye predicados de comparación (>, <, >=, <=) con el tipo correcto del campo.
     */
    @SuppressWarnings("unchecked")
    private <Y extends Comparable<? super Y>> Predicate buildComparisonPredicate(
            CriteriaBuilder cb, Path<?> path, String operator, String value) {
        Expression<Y> expression = (Expression<Y>) path;
        Y converted = (Y) convertValue(path.getJavaType(), value);
        return switch (operator) {
            case ">"  -> cb.greaterThan(expression, converted);
            case "<"  -> cb.lessThan(expression, converted);
            case ">=" -> cb.greaterThanOrEqualTo(expression, converted);
            case "<=" -> cb.lessThanOrEqualTo(expression, converted);
            default   -> null;
        };
    }
}
