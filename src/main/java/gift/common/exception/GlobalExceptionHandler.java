package gift.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ProblemDetail> handlerMethodValidationException(HandlerMethodValidationException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation Error");

        e.getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            problemDetail.setDetail(errorMessage);
        });
        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation Error");

        e.getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            problemDetail.setDetail(errorMessage);
        });
        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(InvalidWordException.class)
    public ResponseEntity<ProblemDetail> invalidWordException(InvalidWordException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation Error");

        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.badRequest().body(problemDetail);
    }

}
