package gift;

public class ValidationException extends RuntimeException {

    private final ProductDTO productDTO;

    public ValidationException(String message, ProductDTO productDTO) {
        super(message);
        this.productDTO = productDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }
}