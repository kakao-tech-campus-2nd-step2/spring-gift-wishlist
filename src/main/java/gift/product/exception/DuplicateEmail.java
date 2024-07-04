package gift.product.exception;

public class DuplicateEmail extends RuntimeException{
    public DuplicateEmail(String message) {
        super(message);
    }
}
