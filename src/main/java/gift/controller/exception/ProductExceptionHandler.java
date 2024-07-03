package gift.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler{

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<String> handleProductException(ProductException productException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productException.getMessage());
    }
}
