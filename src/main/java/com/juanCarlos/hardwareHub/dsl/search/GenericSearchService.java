package com.juanCarlos.hardwareHub.dsl.search;

import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import com.juanCarlos.hardwareHub.dsl.parser.QueryDslParser;
import com.juanCarlos.hardwareHub.dsl.specification.GenericSpecification;
import com.juanCarlos.hardwareHub.dsl.specification.SpecificationBuilder;
import com.juanCarlos.hardwareHub.dsl.util.PageableUtils;
import com.juanCarlos.hardwareHub.dsl.validation.FilterValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Servicio genérico que centraliza toda la lógica de búsqueda con filtros (parsear filtros, validarlos, construir la
 * consulta y paginar). Este servicio lo hace todo en un solo método reutilizable que funciona con cualquier entidad.
 *
 * @author Juan Carlos
 * @see FilterCriteria
 * @see QueryDslParser
 * @see GenericSpecification
 * @see SpecificationBuilder
 * @see PageableUtils
 * @see FilterValidator
 */
@Service
public class GenericSearchService {

    private final QueryDslParser parser = new QueryDslParser();

    public <T> Page<T> search(
            JpaSpecificationExecutor<T> repository,
            String filter,
            int page,
            int size,
            String sort,
            Set<String> allowedFields) {

        List<FilterCriteria> filters = parser.parse(filter);

        FilterValidator.validate(filters, allowedFields);

        Specification<T> spec = new SpecificationBuilder<T>().build(filters);

        Pageable pageable = PageableUtils.createPageable(page, size, sort);

        return repository.findAll(spec, pageable);
    }
}
