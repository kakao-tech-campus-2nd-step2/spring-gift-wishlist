package gift.product.exception;

public class ProductUpdateException extends RuntimeException {

    private final ProductErrorCode errorCode;

    public ProductUpdateException(ProductErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
    }

    public ProductErrorCode getErrorCode() {
        return errorCode;
    }
}
