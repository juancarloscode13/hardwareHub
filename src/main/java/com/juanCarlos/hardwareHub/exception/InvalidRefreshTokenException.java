package com.juanCarlos.hardwareHub.exception;

/**
 * Excepción lanzada cuando un refresh token es inválido, revocado o expirado.
 *
 * @author Juan Carlos
 */
public class InvalidRefreshTokenException extends RuntimeException {
    public InvalidRefreshTokenException(String message) {
        super(message);
    }
}

