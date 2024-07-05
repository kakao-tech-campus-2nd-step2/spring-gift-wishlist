package gift.model;

public class Wish {
    private Long id;
    private Long productId;
    private String userEmail;

    public Wish() {}

    public Wish(Long productId, String userEmail) {
        this.productId = productId;
        this.userEmail = userEmail;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
