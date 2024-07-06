package gift.vo;

public class WishProduct {

    private Long wishProductId;
    private String memberId; //member pk is email
    private Long productId;

    public WishProduct(Long wishProductId, String memberId, Long productId) {
        this.wishProductId = wishProductId;
        this.memberId = memberId;
        this.productId = productId;
    }

    public Long getWishProductId() {
        return wishProductId;
    }

    public String getMemberId() {
        return memberId;
    }

    public Long getProductId() {
        return productId;
    }
}
