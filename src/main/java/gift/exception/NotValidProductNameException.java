package gift.exception;

import gift.model.Product;

public class NotValidProductNameException extends RuntimeException {
    private Product product;
    public NotValidProductNameException(String message, Product product) {
        super(message);
        this.product = product;
    }
    public Product getProduct(){
        return product;
    }
}
