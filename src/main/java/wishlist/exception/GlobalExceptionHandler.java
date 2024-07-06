package wishlist.exception;

import io.jsonwebtoken.JwtException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wishlist.exception.CustomException.ItemNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<wishlist.exception.ErrorResponseDTO> handleItemNotFoundException(
        ItemNotFoundException e) {
        return handleException(e.getErrorCode(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<wishlist.exception.ErrorResponseDTO> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return handleException(ErrorCode.INVALID_INPUT, errors);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<String> handleAuthException(JwtException e) {
        return ResponseEntity.status(ErrorCode.INVALID_TOKEN.getStatus()).body(e.getMessage());
    }

    public ResponseEntity<ErrorResponseDTO> handleException(ErrorCode errorCode,
        Map<String, String> errors) {
        return new ResponseEntity<>(new wishlist.exception.ErrorResponseDTO(errorCode, errors),
            errorCode.getStatus());
    }
}
