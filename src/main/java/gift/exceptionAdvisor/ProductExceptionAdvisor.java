package gift.exceptionAdvisor;


import gift.dto.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ProductExceptionAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class) //유효성 검사 실패 시
    public ResponseEntity<ExceptionResponse> productValidationException(
        MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(new ExceptionResponse(
            exception.getBindingResult().getFieldError().getDefaultMessage()),
            exception.getStatusCode());
    }

    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<ExceptionResponse> productServiceException(
        ProductServiceException exception) {
        return new ResponseEntity<>(new ExceptionResponse(exception.getMessage()),
            exception.getStatusCode());
    }

    @ExceptionHandler(MemberServiceException.class)
    public ResponseEntity<ExceptionResponse> memberServiceException(
        MemberServiceException exception) {
        return new ResponseEntity<>(new ExceptionResponse(exception.getMessage()),
            exception.getStatusCode());
    }
}
