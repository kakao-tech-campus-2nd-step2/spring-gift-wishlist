package gift.dto.wishlist;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record WishRequestDto(
    @NotNull Long productId,
    @Positive int productCount
) {}
