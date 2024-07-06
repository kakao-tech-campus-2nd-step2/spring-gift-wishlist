package gift.api.member;

public class EmailAlreadyExistsException extends RuntimeException {

    private static final String message = "Email already exists.";

    public EmailAlreadyExistsException() {
        super(message);
    }
}
