package com.juanCarlos.hardwareHub.exception;

/**
 * Excepción lanzada cuando un token de recuperación de contraseña es inválido, usado o expirado.
 *
 * @author Juan Carlos
 */
public class InvalidPasswordResetTokenException extends RuntimeException {
    public InvalidPasswordResetTokenException(String message) {
        super(message);
    }
}

