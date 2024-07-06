package gift.exception;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception e) {
        ExceptionResponse error = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(),
            "Internal server error");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

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

    @ExceptionHandler(NoSuchEmailException.class)
    public ResponseEntity<ExceptionResponse> handleNoSuchEmailException(
        NoSuchEmailException e) {
        ExceptionResponse error = new ExceptionResponse(HttpStatus.NOT_FOUND.value(),
            e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException(
        BadCredentialsException e) {
        ExceptionResponse error = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(),
            e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
