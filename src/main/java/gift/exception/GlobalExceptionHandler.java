package gift.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 예외 메시지를 이용해 ResponseEntity를 생성하는 메소드
    protected ResponseEntity<String> buildResponseEntity(String errorMessage, HttpStatus status) {
        return new ResponseEntity<>(errorMessage, status);
    }

    // 일반적인 예외를 처리하는 핸들러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return buildResponseEntity("서버 오류 발생.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // RuntimeException을 처리하는 핸들러
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return buildResponseEntity("인증 실패: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // Validation 예외를 처리하는 핸들러
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations().iterator().next().getMessage();
        return buildResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleMethodArgumentValidException(MethodArgumentNotValidException ex) {
        String errorMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return buildResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
