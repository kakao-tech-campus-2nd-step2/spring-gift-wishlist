package gift.dto;

import jakarta.validation.constraints.PositiveOrZero;

public record ProductOptionRequest(
        Long productId,
        String name,
        @PositiveOrZero Integer additionalPrice) {
}
