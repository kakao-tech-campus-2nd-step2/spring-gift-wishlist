package gift.model;

public class WishlistDTO {
    private long id;
    private long userId;
    private long productId;
    private long amount;

    public WishlistDTO(long id, long userId, long productId, long amount) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
