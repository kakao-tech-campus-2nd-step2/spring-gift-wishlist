package gift.exceptionAdvisor;

public class ProductServiceException extends RuntimeException {

    public ProductServiceException() {
    }

    public ProductServiceException(String message) {
        super(message);
    }
}
