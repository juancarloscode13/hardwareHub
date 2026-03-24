package com.juanCarlos.hardwareHub.exception;

/**
 * Excepción lanzada cuando se intenta registrar un usuario con un email ya existente.
 *
 * @author Juan Carlos
 */
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}

