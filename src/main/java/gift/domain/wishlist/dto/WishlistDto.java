package gift.domain.wishlist.dto;

import jakarta.validation.constraints.NotNull;

public record WishlistDto(@NotNull Long productId, @NotNull Long quantity) {

}
