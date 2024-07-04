package gift.error.exception;

import gift.error.ErrorMessage;

public class ProductNotFoundException extends GiftException {

    public ProductNotFoundException() {
        super(ErrorMessage.PRODUCT_NOT_FOUND);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

}
