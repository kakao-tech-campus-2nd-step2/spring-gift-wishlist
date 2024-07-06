package gift.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateDataException extends RuntimeException {
    private static final String DEFAULT_TITLE = "Duplicate Data";
    private final HttpStatus status;
    private final String title;

    public DuplicateDataException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
        this.title = DEFAULT_TITLE;
    }

    public DuplicateDataException(String message, String title) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
        this.title = title;
    }

    public DuplicateDataException(String message, HttpStatus status, String title) {
        super(message);
        this.status = status;
        this.title = title;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }
}
