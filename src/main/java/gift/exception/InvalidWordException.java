package gift.exception;

public class InvalidWordException extends RuntimeException {

    private final String redirectUrl;

    public InvalidWordException(String message, String redirectUrl) {
        super(message);
        this.redirectUrl = redirectUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
}
