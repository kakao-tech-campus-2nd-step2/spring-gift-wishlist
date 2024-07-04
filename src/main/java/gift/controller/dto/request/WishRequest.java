package gift.controller.dto.request;

import jakarta.validation.constraints.Min;

public record WishRequest(
        @Min(0)
        Long productId
) {
}
