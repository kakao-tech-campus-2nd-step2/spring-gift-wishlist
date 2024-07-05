package gift.exception;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchProductException.class)
    public ProblemDetail handleNoSuchProductException(NoSuchProductException noSuchProductException) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setDetail(noSuchProductException.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        var reasons = methodArgumentNotValidException.getBindingResult()
            .getFieldErrors()
            .stream()
            .collect(Collectors.groupingBy(FieldError::getField, Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));
        problemDetail.setProperties(Map.of("object", reasons));
        problemDetail.setDetail("상품 정보가 잘못 입력되었습니다.");
        return problemDetail;
    }

    @ExceptionHandler({AlreadyExistMemberException.class, NoSuchMemberException.class})
    public ProblemDetail handleAlreadyExistMemberException(RuntimeException runtimeException) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(runtimeException.getMessage());
        return problemDetail;
    }
}
