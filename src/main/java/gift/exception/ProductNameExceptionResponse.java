package gift.exception;

import org.springframework.http.HttpStatus;

public class ProductNameExceptionResponse {
    private int status;
    private HttpStatus httpStatus;
    private String message;

    public ProductNameExceptionResponse(ErrorCode error) {
        this.status = error.getStatus();
        this.message = error.getMessage();
        this.httpStatus = error.getHttpStatus();
    }

    public int getStatus() {
        return status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
