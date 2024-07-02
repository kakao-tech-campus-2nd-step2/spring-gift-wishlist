package gift.exception;

public class PriceLessThanZeroException extends RuntimeException {
    public PriceLessThanZeroException(String message) {
        super(message);
    }
}