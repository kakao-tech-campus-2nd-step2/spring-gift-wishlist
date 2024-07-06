package gift.wishList;

import java.util.List;

public class WishList {
    Long id;
    Long userID;
    Long productID;
    Long count;

    public WishList(Long id, Long userID, Long productID, Long count) {
        this.id = id;
        this.userID = userID;
        this.productID = productID;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
