package gift.member.presentation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record RequestWishlistDto(
    @NotBlank
    @Min(1)
    Integer count
) {
    public WishlistUpdateDto toWishListUpdateDto(Long productId) {
        return new WishlistUpdateDto(productId, count());
    }
}
