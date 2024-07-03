package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException ex, WebRequest request) {
        HashMap<String, Object> body = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        body.put("timestamp", System.currentTimeMillis());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage());
        body.put("path", request.getDescription(false)
            .replace("uri=", ""));
        return ResponseEntity.status(status).body(body);
    }

}
