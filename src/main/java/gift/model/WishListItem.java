package gift.model;

public class WishListItem {

    private Long id;
    private Long wishListId;
    private Long productId;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public WishListItem setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public WishListItem(Long id, Long wishListId, Long productId, int quantity) {
        this.id = id;
        this.wishListId = wishListId;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public WishListItem setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getWishListId() {
        return wishListId;
    }

    public WishListItem setWishListId(Long wishListId) {
        this.wishListId = wishListId;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public WishListItem setProductId(Long productId) {
        this.productId = productId;
        return this;
    }
}