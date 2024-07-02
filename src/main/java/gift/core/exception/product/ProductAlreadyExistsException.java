package gift.core.exception.product;

import gift.core.exception.APIException;
import gift.core.exception.ErrorCode;

public class ProductAlreadyExistsException extends APIException {
    public ProductAlreadyExistsException() {
        super(ErrorCode.PRODUCT_ALREADY_EXISTS);
    }
}
