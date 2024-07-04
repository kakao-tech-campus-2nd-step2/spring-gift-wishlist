package gift.exceptionAdvisor;


import gift.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
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
        MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(
            new ExceptionResponseDTO(ex.getBindingResult().getFieldError().getDefaultMessage()),
            HttpStatus.BAD_REQUEST);
    }
}
