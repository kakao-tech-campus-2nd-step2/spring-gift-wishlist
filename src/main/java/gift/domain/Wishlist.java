package gift.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class Wishlist {

    @NotBlank(message = "유저 Id는 필수 입력 값입니다.")
    private Long userId;

    @NotBlank(message = "상품 Id는 필수 입력 값입니다.")
    private Long ProductId;

    @PositiveOrZero(message = "수량은 0개 이상이어야 합니다.")
    private int quantity;

    public Wishlist(Long userId, Long productId, int quantity) {
        this.userId = userId;
        ProductId = productId;
        this.quantity = quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
