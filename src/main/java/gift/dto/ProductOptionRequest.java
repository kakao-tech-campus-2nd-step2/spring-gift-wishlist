package gift.dto;

import jakarta.validation.constraints.PositiveOrZero;

public record ProductOptionRequest(
        Long productId,
        String name,
        @PositiveOrZero(message = "추가 금액은 0보다 크거나 같아야 합니다.")
        Integer additionalPrice) {
}
