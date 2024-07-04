package gift.exception;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(
        IllegalArgumentException e) {
        ExceptionResponse error = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(),
            e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> handleNoSuchElementException(
        NoSuchElementException e) {
        ExceptionResponse error = new ExceptionResponse(HttpStatus.NOT_FOUND.value(),
            e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateEmailException(
        DuplicateEmailException e) {
        ExceptionResponse error = new ExceptionResponse(HttpStatus.CONFLICT.value(),
            e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
