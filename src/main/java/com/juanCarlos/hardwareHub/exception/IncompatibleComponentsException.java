package com.juanCarlos.hardwareHub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Excepción para cuando se seleccionen componentes incompatibles entre sí.
 *
 * @author Juan Carlos
 */

public class IncompatibleComponentsException extends ResponseStatusException {
    public IncompatibleComponentsException() {
        super(HttpStatus.CONFLICT, "Los componentes seleccionados no son compatibles entre sí");
    }
}
