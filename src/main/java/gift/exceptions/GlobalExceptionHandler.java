package gift.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(KakaoContainException.class)
    public ResponseEntity<Map<String, String>> handleKakaoContainException(KakaoContainException kex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", kex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidNameExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // 첫 번째 오류 메시지를 사용
        String firstErrorMessage = errors.values().iterator().next();
        Map<String, String> response = new HashMap<>();
        response.put("message", firstErrorMessage);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}


