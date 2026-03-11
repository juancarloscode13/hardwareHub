package com.juanCarlos.hardwareHub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.juanCarlos.hardwareHub.dto.response.ErrorResponse;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * Manejador de excepciones global.
 *
 * @author Juan Carlos
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manejador de IncompatibleComponentsException.
     *
     * @return Respuesta Http con detalles del error.
     * @see IncompatibleComponentsException
     */
    @ExceptionHandler(IncompatibleComponentsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse>handleIncompatibleComponentsException(IncompatibleComponentsException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Manejador para NoSuchElementException.
     *
     * @return Respuesta Http con detalles del error.
     * @see NoSuchElementException
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse>handleNoSuchElementException(NoSuchElementException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Manejador para ForbiddenFieldException
     *
     * @return Respuesta Http con detalles del error.
     * @see ForbiddenFieldException
     */
    @ExceptionHandler(ForbiddenFieldException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse>handleForbiddenFieldException(ForbiddenFieldException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }
}
