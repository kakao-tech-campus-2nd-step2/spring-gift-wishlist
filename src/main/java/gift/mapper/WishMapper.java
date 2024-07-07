package gift.mapper;

import gift.dto.wishlist.WishRequestDto;
import gift.entity.Wish;

public class WishMapper {

    public static Wish toWish(Long userId, WishRequestDto wishRequest) {
        return new Wish(userId, wishRequest.productId(), wishRequest.quantity());
    }

    public static Wish toWish(WishRequestDto wishRequest) {
        return new Wish(null, wishRequest.productId(), wishRequest.quantity());
    }

}
