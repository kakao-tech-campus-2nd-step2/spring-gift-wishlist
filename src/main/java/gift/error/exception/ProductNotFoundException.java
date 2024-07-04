package gift.error.exception;

public class ProductNotFoundException extends GiftException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

}
