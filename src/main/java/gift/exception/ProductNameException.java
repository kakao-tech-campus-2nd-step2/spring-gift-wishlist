package gift.exception;

public class ProductNameException extends RuntimeException{
    private int status;
    private ErrorCode errorCode;
    private String message;

    public ProductNameException(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    public int getStatus() {
        return status;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
