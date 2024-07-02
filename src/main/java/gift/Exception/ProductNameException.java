package gift.Exception;

public class ProductNameException extends IllegalArgumentException{
    private ErrorCode errorCode;
    public ProductNameException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
