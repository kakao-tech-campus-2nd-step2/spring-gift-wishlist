package gift.vo;

public class WishProduct {

    private Long wishProductId;
    private String memberId; //Member pk는 String-email. Member 데이터 구조가 변경된다면 이 필드 타입도 변경 필요
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
