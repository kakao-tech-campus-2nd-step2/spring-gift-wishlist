package gift.entity;

public class WishList {
    private final int user_id;
    private final int product_id;

    public WishList(int user_id, int product_id) {
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getProduct_id() {
        return product_id;
    }
}
