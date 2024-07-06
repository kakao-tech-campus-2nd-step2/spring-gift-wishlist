package gift.member.persistence.entity;

public class Wishlist {
    private Long id;
    private Long productId;
    private Long memberId;
    private Integer count;

    public Wishlist(long id, long productId, long memberId, int count) {
        this.id = id;
        this.productId = productId;
        this.memberId = memberId;
        this.count = count;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getId() {
        return id;
    }

    public Integer getCount() {
        return count;
    }
}
