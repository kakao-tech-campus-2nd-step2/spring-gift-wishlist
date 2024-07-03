package gift.error;

import gift.error.exception.ProductNotFoundException;
import gift.error.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> validate(ValidationException e) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponse(e.getMessage(), e.getErrors()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> notfound(ProductNotFoundException e) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponse(e.getMessage(), null));
    }

}
