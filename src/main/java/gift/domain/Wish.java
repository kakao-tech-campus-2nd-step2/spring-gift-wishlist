package gift.domain;

public class Wish {
    private Long id;
    private String userEmail;
    private String productName;
    private int quantity;

    public Wish(Long id, String userEmail, String productName, int quantity) {
        this.id = id;
        this.userEmail = userEmail;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}