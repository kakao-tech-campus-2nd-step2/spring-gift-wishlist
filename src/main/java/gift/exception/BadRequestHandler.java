package gift.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class BadRequestHandler extends BadRequestExceptionHandler{
    /**
     * handle Validation Exception
     * @param ex Exception.class
     * @return ResponseEntity<String>
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<String> handleConstraintViolationException(
            ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations().iterator().next().getMessage();
        return buildResponseEntity(errorMessage);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleMethodArgumentValidException(
            MethodArgumentNotValidException ex) {
        String errorMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return buildResponseEntity(errorMessage);
    }
}
