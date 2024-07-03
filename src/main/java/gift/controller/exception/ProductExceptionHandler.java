package gift.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class ProductExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(methodArgumentNotValidException.getMessage());
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<String> handleProductException(ProductException productException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productException.getMessage());
    }
}
