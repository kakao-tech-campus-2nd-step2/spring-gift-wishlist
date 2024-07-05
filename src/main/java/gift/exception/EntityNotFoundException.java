package gift.exception;

public class EntityNotFoundException extends RuntimeException{

    private static final String MESSAGE = "해당 상품을 찾을 수 없습니다.";

    public EntityNotFoundException(){
        super(MESSAGE);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
