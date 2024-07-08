package gift.domain.dto;

import gift.domain.entity.User;
import gift.domain.entity.Wishlist;
import jakarta.validation.constraints.NotNull;

public record WishlistRequestDto(@NotNull Long productId, @NotNull Long quantity) {

    public static WishlistRequestDto of(Wishlist wishlist) {
        return new WishlistRequestDto(wishlist.productId(), wishlist.quantity());
    }

    public static Wishlist toEntity(WishlistRequestDto dto, User user) {
        return new Wishlist(dto.productId, user.email(), dto.quantity);
    }
}
