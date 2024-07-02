package gift.exception;

public class InvalidIdException extends CustomException {
    public InvalidIdException() {
        super();
    }

    public InvalidIdException(String message) {
        super(message);
    }
}
