package com.juanCarlos.hardwareHub.dsl.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Utilidad para crear la paginación y el ordenamiento de los resultados. A partir del número de página, el tamaño y
 * el campo de ordenación, genera el objeto Pageable que necesita Spring para paginar las consultas.
 *
 * @author Juan Carlos
 */
public class PageableUtils {

    public static Pageable createPageable(int page, int size, String sort) {
        if (sort == null || sort.isBlank())
            return PageRequest.of(page, size);

        String normalized = sort.trim();

        // Formato legacy: -campo
        if (normalized.startsWith("-")) {
            return PageRequest.of(page, size, Sort.by(normalized.substring(1)).descending());
        }

        // Formato recomendado por frontend: campo:desc | campo:asc
        if (normalized.contains(":")) {
            String[] parts = normalized.split(":", 2);
            String property = parts[0].trim();
            String direction = parts[1].trim();
            Sort.Direction dir = "desc".equalsIgnoreCase(direction)
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC;
            return PageRequest.of(page, size, Sort.by(dir, property));
        }

        // Compatibilidad extra: campo,desc | campo,asc
        if (normalized.contains(",")) {
            String[] parts = normalized.split(",", 2);
            String property = parts[0].trim();
            String direction = parts[1].trim();
            Sort.Direction dir = "desc".equalsIgnoreCase(direction)
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC;
            return PageRequest.of(page, size, Sort.by(dir, property));
        }

        return PageRequest.of(page, size, Sort.by(normalized).ascending());
    }
}
