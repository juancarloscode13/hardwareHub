package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IncompatibleComponentsException extends ResponseStatusException {
    public IncompatibleComponentsException() {
        super(HttpStatus.CONFLICT, "Los componentes seleccionados no son compatibles entre sí");
    }
}
