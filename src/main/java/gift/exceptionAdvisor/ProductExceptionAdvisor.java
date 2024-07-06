package gift.exceptionAdvisor;


import gift.dto.ExceptionResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ProductExceptionAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class) //유효성 검사 실패 시
    public ResponseEntity<ExceptionResponseDTO> productValidationException(
        MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(new ExceptionResponseDTO(
            exception.getBindingResult().getFieldError().getDefaultMessage()),
            exception.getStatusCode());
    }

    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<ExceptionResponseDTO> productServiceException(
        ProductServiceException exception) {
        return new ResponseEntity<>(new ExceptionResponseDTO(exception.getMessage()),
            exception.getStatusCode());
    }

    @ExceptionHandler(MemberServiceException.class)
    public ResponseEntity<ExceptionResponseDTO> memberServiceException(
        MemberServiceException exception) {
        return new ResponseEntity<>(new ExceptionResponseDTO(exception.getMessage()),
            exception.getStatusCode());
    }
}
