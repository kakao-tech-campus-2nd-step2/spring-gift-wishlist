package gift.Exception;

public class ProductErrorResponse {
    private int status;
    private String message;

    public ProductErrorResponse(ErrorCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
