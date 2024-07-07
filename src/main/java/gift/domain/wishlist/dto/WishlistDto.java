package gift.domain.wishlist.dto;

import gift.domain.user.User;
import gift.domain.wishlist.Wishlist;
import jakarta.validation.constraints.NotNull;

public record WishlistDto(@NotNull Long productId, @NotNull Long quantity) {

    public static WishlistDto of(Wishlist wishlist) {
        return new WishlistDto(wishlist.productId(), wishlist.quantity());
    }

    public static Wishlist toEntity(WishlistDto dto, User user) {
        return new Wishlist(dto.productId, user.email(), dto.quantity);
    }
}
