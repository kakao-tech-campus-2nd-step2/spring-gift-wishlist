package gift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // 유효성 검사 오류를 간단한 형식으로 변환
        List<Map<String, String>> invalidParams = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
            Map<String, String> error = new HashMap<>();
            error.put("field", fieldError.getField()); // 필드 이름
            error.put("rejectedValue", String.valueOf(fieldError.getRejectedValue())); // 거부된 값
            error.put("reason", fieldError.getDefaultMessage()); // 오류 메시지
            return error;
        }).collect(Collectors.toList());

        // 응답 객체 초기화 및 순서 변경
        Map<String, Object> response = new HashMap<>();
        response.put("type", "http://localhost:8080/api/products/validation-error");
        response.put("title", "Your request parameters didn't validate.");
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("invalid-params", invalidParams); // 응답에 추가

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 응답 반환
    }
}
