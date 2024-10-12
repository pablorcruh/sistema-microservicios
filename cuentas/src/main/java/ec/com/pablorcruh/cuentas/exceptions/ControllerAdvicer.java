package ec.com.pablorcruh.cuentas.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ControllerAdvicer extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage());
        return buildErrorResponse(e, e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleInsufficientBalanceException(InsufficientBalanceException e) {
        log.error(e.getMessage());
        return buildErrorResponse(e, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ResponseEntity<Object> buildErrorResponse(Exception exception, String message, HttpStatus httpStatus) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

}
