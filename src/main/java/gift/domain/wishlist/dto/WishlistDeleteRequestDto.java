package gift.domain.wishlist.dto;

import gift.domain.user.User;
import gift.domain.wishlist.Wishlist;

public record WishlistDeleteRequestDto(Long productId) {

    public static Wishlist toEntity(WishlistDeleteRequestDto dto, User user) {
        return new Wishlist(dto.productId, user.email(), 0L);
    }
}
