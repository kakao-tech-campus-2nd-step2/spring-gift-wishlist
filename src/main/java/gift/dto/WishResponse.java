package gift.dto;

public class WishResponse {

    private Long id;
    private String productName;
    private Long memberId;

    public WishResponse() {}

    public WishResponse(Long id, String productName, Long memberId) {
        this.id = id;
        this.productName = productName;
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}