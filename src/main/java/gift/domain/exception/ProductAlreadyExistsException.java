package gift.domain.exception;

import gift.domain.Entity.Product;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException() {
        super("Product already exists");
    }
}
