package gift.service;

import gift.dao.WishDAO;
import gift.domain.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {

    @Autowired
    private WishDAO wishDAO;

    public List<Wish> getWishes(Long memberId) {
        return wishDAO.findByMemberId(memberId);
    }

    public Wish addWish(String productName, Long memberId) {
        Wish wish = new Wish(productName, memberId);
        return wishDAO.save(wish);
    }

    public void removeWish(Long wishId) {
        wishDAO.deleteById(wishId);
    }
}
