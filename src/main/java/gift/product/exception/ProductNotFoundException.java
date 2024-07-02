package gift.product.exception;

public class ProductNotFoundException extends RuntimeException {

        private final ProductErrorCode errorCode;

        public ProductNotFoundException(ProductErrorCode errorCode) {
            super(errorCode.getDetail());
            this.errorCode = errorCode;
        }

        public ProductErrorCode getErrorCode() {
            return errorCode;
        }

}
