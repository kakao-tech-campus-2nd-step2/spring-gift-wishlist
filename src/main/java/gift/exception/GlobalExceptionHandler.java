package gift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map; // 오류 메시지 저장 맵

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 특정 예외가 발생 했을 때 () 안에 메소드가 호출되도록 하는 어노테이션
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // 유효성 검사 실패 필드랑 메시지 추출
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        // 클라이언트에 응답 반환
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // 상품명에 '카카오' 사용한 경우에 호출되도록 하는 어노테이션
    @ExceptionHandler(KakaoProductException.class)
    public ResponseEntity<String> handleKakaoProductException(KakaoProductException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}