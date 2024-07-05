package gift.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidWordException extends RuntimeException{
    public InvalidWordException(String message) {
        super(message);
    }
}
