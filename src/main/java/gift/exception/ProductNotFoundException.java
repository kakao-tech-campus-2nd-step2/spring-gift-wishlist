package gift.exception;

public class ProductNotFoundException extends RuntimeException{

    private static final String MESSAGE = "해당 상품을 찾을 수 없습니다.";

    public ProductNotFoundException(){
        super(MESSAGE);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
