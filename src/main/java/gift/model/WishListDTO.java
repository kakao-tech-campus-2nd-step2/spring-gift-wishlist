package gift.model;

import gift.util.ProductIdConstraint;

public class WishListDTO {

    @ProductIdConstraint
    private Long productId;
    private int count;

    public WishListDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
