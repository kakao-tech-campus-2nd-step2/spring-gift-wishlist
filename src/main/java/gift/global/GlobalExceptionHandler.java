package gift.global;

import gift.domain.product.exception.ProductAlreadyExistsException;
import gift.domain.product.exception.ProductNotFoundException;
import gift.domain.user.exception.UserAlreadyExistsException;
import gift.domain.user.exception.UserIncorrectLoginInfoException;
import gift.global.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 서버에서 발생하는 예외를 종합적으로 처리하는 클래스
 */
@RestControllerAdvice(basePackages = {"gift.domain", "gift.global"})
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError error = e.getBindingResult().getFieldError();
        assert error != null;
        return ErrorResponse.of(error.getField() + ": " + error.getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFoundException(ProductNotFoundException e) {
        return ErrorResponse.notFound(e);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleProductAlreadyExistsException(ProductAlreadyExistsException e) {
        return ErrorResponse.conflict(e);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleUUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ErrorResponse.conflict(e);
    }

    @ExceptionHandler(UserIncorrectLoginInfoException.class)
    public ResponseEntity<Map<String, Object>> handleUUserAlreadyExistsException(UserIncorrectLoginInfoException e) {
        return ErrorResponse.of(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Map<String, Object>> handleExpiredJwtException(ExpiredJwtException e) {
        return ErrorResponse.of(new RuntimeException("토큰이 만료되었습니다. 재발급이 필요합니다."), HttpStatus.FORBIDDEN);
    }
}
