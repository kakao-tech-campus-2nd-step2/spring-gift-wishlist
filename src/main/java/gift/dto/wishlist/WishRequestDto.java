package gift.dto.wishlist;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record WishRequestDto(
    @NotNull Long productId,
    @Min(value = 0) int productCount
) {}
