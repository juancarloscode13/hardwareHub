package com.juanCarlos.hardwareHub.dsl.validation;

import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import com.juanCarlos.hardwareHub.exception.ForbiddenFieldException;

import java.util.List;
import java.util.Set;

public class FilterValidator {

    public static void validate(List<FilterCriteria>filters, Set<String> allowedFields){
        if (filters == null || filters.isEmpty()) return;

        for (FilterCriteria filter : filters){

            if (!allowedFields.contains(filter.getField())){
                throw new ForbiddenFieldException();
            }
        }
    }
}
