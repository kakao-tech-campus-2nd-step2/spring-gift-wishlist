package gift.controller;

import jakarta.validation.constraints.NotNull;

public record WishlistRequest(
    @NotNull(message = "Product ID is required")
    Long productId
) {
}