package gift;

public class WishList {
    private Long id;
    private Long memberId;
    private Long productId;

    public WishList(Long id, Long memberId, Long productId) {
        this.id = id;
        this.memberId = memberId;
        this.productId = productId;
    }
}
