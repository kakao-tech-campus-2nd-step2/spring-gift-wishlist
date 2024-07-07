package gift.mapper;

import gift.dto.wishlist.WishRequestDto;
import gift.entity.Wish;

public class WishMapper {

    public static Wish toWish(Long userId, WishRequestDto wishRequest) {
        return new Wish(userId, wishRequest.productId(), wishRequest.quantity());
    }

}
