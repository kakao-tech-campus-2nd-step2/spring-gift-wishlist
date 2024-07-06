package gift.mapper;

import gift.dto.wishlist.WishRequestDto;
import gift.entity.Wishlist;

public class WishlistMapper {

    public static Wishlist toWishlist(Long userId, WishRequestDto wishRequest) {
        return new Wishlist(userId, wishRequest.productId(), wishRequest.quantity());
    }

}
