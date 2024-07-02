package gift.product.exception;

public class ProductCreateException extends RuntimeException {

    private final ProductErrorCode errorCode;

    public ProductCreateException(ProductErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
    }

    public ProductErrorCode getErrorCode() {
        return errorCode;
    }

}
