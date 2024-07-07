package gift.dto;

public class WishListDto {

    
    private long productId;
    private long userId;

    public WishListDto(long productId, long userId) {
        this.productId = productId;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
