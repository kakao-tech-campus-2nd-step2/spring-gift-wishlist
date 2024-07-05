package gift.service;

import gift.dao.WishDAO;
import gift.dto.WishRequest;
import gift.entity.Wish;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishService {

    private final WishDAO wishDAO;

    public WishService(WishDAO wishDAO) {
        this.wishDAO = wishDAO;
    }

    public void addWish(Long userId, WishRequest request) {
        Wish wish = new Wish();
        wish.setUserId(userId);
        wish.setProductId(request.getProductId());
        wishDAO.save(wish);
    }

    public List<Wish> getWishes(Long userId) {
        return wishDAO.findByUserId(userId);
    }

    public void removeWish(Long userId, Long wishId) {
        wishDAO.deleteByUserIdAndWishId(userId, wishId);
    }
}
