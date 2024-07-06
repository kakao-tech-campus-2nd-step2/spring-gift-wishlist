package gift.web.dto.request.wishproduct;

import jakarta.validation.constraints.Min;

public class CreateWishProductRequest {

    private Long productId;
    @Min(1)
    private Integer quantity;

    public CreateWishProductRequest(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
