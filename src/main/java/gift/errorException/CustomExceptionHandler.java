package gift.errorException;

import gift.domain.StatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<StatusResponse> handleResponseStatusException(
        ResponseStatusException ex, WebRequest request) {
        StatusResponse errorResponse = new StatusResponse(ex.getStatusCode().value(),
            ex.getReason());
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

}
