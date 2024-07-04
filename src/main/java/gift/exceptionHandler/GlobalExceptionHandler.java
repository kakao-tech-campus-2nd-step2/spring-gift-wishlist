package gift.exceptionHandler;

import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 입력 값 유효성 검증 실패 시, 에러 핸들링
     *
     * @return 404 Not Found 상태코드와 함께 메시지를 반환
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> inputValidException(MethodArgumentNotValidException e) {
        String errorMsg = Objects.requireNonNull(e.getBindingResult().getFieldError(),
            "에러 필드가 없습니다.").getDefaultMessage();

        return ResponseEntity.badRequest().body(errorMsg);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementExceptionHandler(NoSuchElementException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
