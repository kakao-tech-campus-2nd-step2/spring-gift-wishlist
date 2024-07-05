package gift.exception;

public class DuplicatedWishException extends RuntimeException {

    public DuplicatedWishException() {
        super();
    }

    public DuplicatedWishException(String message) {
        super(message);
    }

    public DuplicatedWishException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedWishException(Throwable cause) {
        super(cause);
    }

    protected DuplicatedWishException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
