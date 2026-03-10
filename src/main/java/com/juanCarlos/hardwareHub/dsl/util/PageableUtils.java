package com.juanCarlos.hardwareHub.dsl.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {

    public static Pageable createPageable(int page, int size, String sort){
        if (sort == null)
            return PageRequest.of(page, size);

        if (sort.startsWith("-"))
            return PageRequest.of(page, size, Sort.by(sort.substring(1)).descending());

        return PageRequest.of(page, size, Sort.by(sort).ascending());
    }
}
