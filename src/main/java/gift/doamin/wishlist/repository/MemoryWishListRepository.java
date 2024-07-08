package gift.doamin.wishlist.repository;

import gift.doamin.wishlist.entity.WishList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryWishListRepository implements WishListRepository {

    private final Map<Long, WishList> wishLists = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public WishList save(WishList wishList) {
        Long id = sequence.getAndIncrement();
        wishList.setId(id);
        wishLists.put(id, wishList);
        return wishList;
    }

    @Override
    public List<WishList> findByUserId(Long userId) {
        List<WishList> result = new ArrayList<>();

        for (WishList wishList : wishLists.values()) {
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

    @Override
    public WishList update(WishList wishList) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }
}
