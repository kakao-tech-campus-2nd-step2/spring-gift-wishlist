package gift.repository;

import gift.model.WishListItem;

import java.util.List;

public interface WishListRepository {
    void addWishListItem(WishListItem item);
    void removeWishListItem(Long id);
    List<WishListItem> findWishListByMemberId(Long memberId);
}
