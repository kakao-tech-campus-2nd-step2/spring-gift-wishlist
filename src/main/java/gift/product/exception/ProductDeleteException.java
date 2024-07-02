package gift.product.exception;

public class ProductDeleteException extends RuntimeException {

    private final ProductErrorCode errorCode;

    public ProductDeleteException(ProductErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
    }

    public ProductErrorCode getErrorCode() {
        return errorCode;
    }
}
