package gift;

public class WishList {
    private Long id;
    private Long memberId;
    private Long productId;

    public WishList() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
