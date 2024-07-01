package gift.exception;

public class LengthExceededException extends RuntimeException {
    public LengthExceededException(String message) {
        super(message);
    }
}