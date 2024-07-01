package gift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = PriceLessThanZeroException.class)
    public ResponseEntity<String> priceLessThanZeroExceptionHandling(){
        return new ResponseEntity<>("금액(비용)은 0원보다 작을 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
