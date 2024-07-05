package gift.domain.user.exception;

public class UserIncorrectLoginInfoException extends RuntimeException {

    public UserIncorrectLoginInfoException() {
        super("Incorrect your email or password. Try again.");
    }
}
