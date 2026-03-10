package com.juanCarlos.hardwareHub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ForbiddenFieldException extends ResponseStatusException {
    public ForbiddenFieldException() {
        super(HttpStatus.FORBIDDEN, "El campo al que intenta acceder está protegido.");
    }
}
