package gift.model;

public class WishList {
    private Long userId;
    private Long wishId;
    private String wishName;
    private int wishPrice;


    public WishList(Long userId,Long wishId, String wishName, int wishPrice ) {
        this.userId = userId;
        this.wishId = wishId;
        this.wishName = wishName;
        this.wishPrice = wishPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getWishId() {
        return wishId;
    }

    public String getWishName() {
        return wishName;
    }

    public int getWishPrice() {
        return wishPrice;
    }
}

