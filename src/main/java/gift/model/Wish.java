package gift.model;

public class Wish {
    private Long id;
    private Long productId;
    private Long userId;
    private Integer amount;

    public Wish(Long id, Long productId, Long userId, Integer amount) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.amount = amount;
    }

    public Wish(Long productId, Long userId, Integer amount) {
        this.productId = productId;
        this.userId = userId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public boolean isNew() {
        return id == null;
    }
}
