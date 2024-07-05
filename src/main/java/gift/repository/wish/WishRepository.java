package gift.repository.wish;

import gift.domain.Wish;

import java.util.List;
import java.util.Optional;

public interface WishRepository {


    Integer findWishCountByWishIdAndMemberId(Long likesId, String email);
    List<Wish> findWishByMemberId(String email);

    Long wishSave(Long productId, String email, int count);

    Long updateWish(Long likesId, int count);

    Long deleteWish(Long likesId);
}
