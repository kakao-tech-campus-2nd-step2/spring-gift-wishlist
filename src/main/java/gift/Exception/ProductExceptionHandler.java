package gift.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
