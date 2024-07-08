package gift.vo;

public class WishProduct {

    private Long wishProductId;
    private String memberId; //Member 객체의 pk는 email로 사용된다. 여기서 사용하는 memberId 역시 Member의 email이다.
    private Long productId;

    public WishProduct(String memberId, Long productId) {
        this(null, memberId, productId);
    }

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
