package gift.wishList;

public class WishListDTO {
    Long productID;

    Long count;

    public WishListDTO() {
    }


    public WishListDTO(WishList wishList) {
        this.productID = wishList.productID;
        this.count = wishList.count;
    }

    public WishListDTO(Long productID, Long count) {
        this.productID = productID;
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

}
