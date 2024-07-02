package gift.exception;

public class DuplicateProductIdException extends RuntimeException {
    public DuplicateProductIdException(String message) {
        super(message);
    }
}
