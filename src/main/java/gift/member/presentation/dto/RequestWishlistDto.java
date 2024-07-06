package gift.member.presentation.dto;

import gift.member.presentation.restcontroller.WishlistUpdateDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record RequestWishlistDto(
    @NotBlank
    Long productId,
    @NotBlank
    @Min(1)
    Integer count
) {
    public WishlistUpdateDto toWishListUpdateDto() {
        return new WishlistUpdateDto(productId(), count());
    }
}
