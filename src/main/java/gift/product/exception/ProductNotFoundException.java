package gift.product.exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException() {
        super("Product Not Found Exception");
    }
}
