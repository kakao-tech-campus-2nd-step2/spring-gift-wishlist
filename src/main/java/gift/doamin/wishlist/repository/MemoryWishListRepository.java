package gift.doamin.wishlist.repository;

import gift.doamin.wishlist.entity.WishList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryWishListRepository implements WishListRepository{

    private final List<WishList> wishLists = new Vector<>();

    @Override
    public void save(WishList wishList) {
        wishLists.add(wishList);
    }

    @Override
    public List<WishList> findAll(Long userId) {
        return null;
    }

    @Override
    public List<WishList> findByUserId(Long userId) {
        List<WishList> result = new ArrayList<>();

        for(WishList wishList : wishLists) {
            if (wishList.getUserId().equals(userId)) {
                result.add(wishList);
            }
        }

        return result;
    }

    @Override
    public WishList findByUserIdAndProductId(Long userId, Long productId) {
        return null;
    }
}
