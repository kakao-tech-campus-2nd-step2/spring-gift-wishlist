package gift.model;

public class WishProduct {
    private Long memberId;
    private Long productId;
    private Integer quantity;

    public WishProduct(Long memberId, Long productId, Integer quantity) {
        this.memberId = memberId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
