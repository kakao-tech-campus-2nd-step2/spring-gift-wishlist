package gift.domain.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WishUpdateRequestDto {

    @NotNull
    @Min(value = 1)
    @Max(value = 100)
    private Integer count;

    @NotNull
    private Long productId;

    public Integer getCount() {
        return count;
    }

    public Long getProductId() {
        return productId;
    }

    public WishUpdateRequestDto(Integer count, Long productId) {
        this.count = count;
        this.productId = productId;
    }
}
