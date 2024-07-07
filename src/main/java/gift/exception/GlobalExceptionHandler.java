package gift.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<?> globalExceptionHandler(CustomException exception) {
        return ResponseEntity.status(exception.getErrorCode().getStatus())
                .body(new ExceptionResponse(exception.getErrorCode()));
    }
}
