package wishlist.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleItemNotFoundException(ItemNotFoundException e) {
        return handleException(e.getErrorCode(), null);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpRequestMethodNotSupportedException(
        HttpRequestMethodNotSupportedException e) {
        return handleException(ErrorCode.METHOD_NOT_ALLOWED, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return handleException(ErrorCode.INVALID_INPUT, errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleCommonException(Exception e) {
        return handleException(ErrorCode.BAD_REQUEST, null);
    }

    public ResponseEntity<ErrorResponseDTO> handleException(ErrorCode errorCode,
        Map<String, String> errors) {
        return new ResponseEntity<>(new ErrorResponseDTO(errorCode, errors), errorCode.getStatus());
    }
}
