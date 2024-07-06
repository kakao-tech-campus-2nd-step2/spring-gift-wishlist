package gift.member.persistence.repository;

import gift.member.persistence.entity.Wishlist;
import java.util.List;

public interface WishlistRepository {

    List<Wishlist> getWishListByMemberId(Long memberId);
}
