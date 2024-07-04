package gift.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateWishException extends RuntimeException {
    public DuplicateWishException(String message) {
        super(message);
    }
}
