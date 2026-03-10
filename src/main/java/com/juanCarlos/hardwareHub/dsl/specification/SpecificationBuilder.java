package com.juanCarlos.hardwareHub.dsl.specification;

import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SpecificationBuilder<T> {

    public Specification<T> build(List<FilterCriteria>filters){

        Specification<T>spec = Specification.where((Specification<T>) null);

        for (FilterCriteria filter : filters){
            spec = spec.and(new GenericSpecification<>(filter));
        }
        return spec;
    }
}
