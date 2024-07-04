package gift.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNameException.class)
    @ResponseBody
    public ResponseEntity<?> productNameExceptionHandler(ProductNameException exception) {
        return ResponseEntity.status(exception.getErrorCode().getStatus())
                .body(new ProductNameExceptionResponse(exception.getErrorCode()));
    }
}
