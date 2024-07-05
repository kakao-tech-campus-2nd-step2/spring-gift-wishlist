package gift.global;
import gift.domain.exception.ProductAlreadyExistsException;
import gift.domain.exception.ProductNotExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest()
            .body(ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<String> handleProductAlreadyExistsException(ProductAlreadyExistsException ex) {
        return ResponseEntity.badRequest()
            .body(ex.getMessage());
    }

    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<String> handleProductNotExistsException(ProductNotExistsException ex) {
        return ResponseEntity.badRequest()
            .body(ex.getMessage());
    }
}
