package gift;

public class RequestWishDto {
    private Long productId;

    public RequestWishDto(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
