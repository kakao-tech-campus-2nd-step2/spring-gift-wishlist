package gift.model;

public class WishList {
    private Long id;
    private Long memberId;

    public WishList(Long id, Long memberId) {
        this.id = id;
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public WishList setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getMemberId() {
        return memberId;
    }

    public WishList setMemberId(Long memberId) {
        this.memberId = memberId;
        return this;
    }
}