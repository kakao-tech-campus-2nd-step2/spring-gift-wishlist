package gift.doamin.wishlist.repository;

import gift.doamin.wishlist.entity.WishList;
import java.util.List;

public interface WishListRepository {

    public void save(WishList wishList);

    public List<WishList> findAll(Long userId);

    public List<WishList> findByUserId(Long userId);

    public WishList findByUserIdAndProductId(Long userId, Long productId);

}
