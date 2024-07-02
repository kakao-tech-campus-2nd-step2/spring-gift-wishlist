package gift.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {
    //테스트
    @ExceptionHandler(value = ProductNameException.class)
    public ResponseEntity<ProductErrorResponse> handleIllegalArgumentException(ProductNameException productNameException){
        ProductErrorResponse productErrorResponse = new ProductErrorResponse(productNameException.getErrorCode());
        return new ResponseEntity<>(productErrorResponse, HttpStatus.valueOf(productNameException.getErrorCode().getStatus()));
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(e.getBindingResult().getFieldErrors().getFirst().getDefaultMessage() ,HttpStatus.BAD_REQUEST);
    }
}
