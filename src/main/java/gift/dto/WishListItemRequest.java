package gift.dto;

public class WishListItemRequest {
    private Long productId;

    public WishListItemRequest(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
