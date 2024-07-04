package gift.controller.dto.request;

import jakarta.validation.constraints.Min;

public record WishRequest(
        @Min(1)
        Long productId,
        @Min(1)
        Long productCount
) {
}
