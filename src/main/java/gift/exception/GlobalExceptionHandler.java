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

    @ExceptionHandler(value = NotFoundElementException.class)
    public ResponseEntity<String> notFoundElementExceptionHandling(){
        return new ResponseEntity<>("존재하지 않는 리소스에 대한 접근입니다.", HttpStatus.NOT_FOUND);
    }
}
