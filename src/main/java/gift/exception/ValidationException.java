package gift.exception;

// 추가한 예외
public class ValidationException extends IllegalArgumentException {
    public ValidationException(String message) {
        super(message);
    }
}
