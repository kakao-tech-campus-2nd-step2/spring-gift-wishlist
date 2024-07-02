package gift.global;

import gift.domain.product.exception.ProductAlreadyExistsException;
import gift.domain.product.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 서버에서 발생하는 예외를 종합적으로 처리하는 클래스
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Void> handleProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Void> handleProductAlreadyExistsException(ProductAlreadyExistsException e, Model model) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
}
