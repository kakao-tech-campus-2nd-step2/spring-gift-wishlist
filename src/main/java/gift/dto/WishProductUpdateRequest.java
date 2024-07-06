package gift.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record WishProductUpdateRequest(
        @NotNull(message = "상품은 반드시 선택되어야 합니다.")
        Long productId,
        @PositiveOrZero(message = "상품의 수량은 반드시 0개 이상이어야 합니다.")
        Integer count) {
}
