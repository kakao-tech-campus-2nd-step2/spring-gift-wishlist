package gift.product.exception;

import gift.global.exception.BusinessException;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException() {
        super("Product Not Found Exception");
    }
}
