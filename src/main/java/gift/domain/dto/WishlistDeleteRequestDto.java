package gift.domain.dto;

import gift.domain.entity.User;
import gift.domain.entity.Wishlist;

public record WishlistDeleteRequestDto(Long productId) {

    public static Wishlist toEntity(WishlistDeleteRequestDto dto, User user) {
        return new Wishlist(dto.productId, user.email(), 0L);
    }
}
