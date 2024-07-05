package gift.Service;

import gift.Model.WishListItem;
import gift.Repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;

    @Autowired
    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public List<WishListItem> getWishlist(int userId) {
        return wishListRepository.getWishlist(userId);
    }

    public void addWishlistItem(int userId, int productId) {
        wishListRepository.addWishlistItem(userId, productId);
    }

    public void removeWishlistItem(int userId, int productId) {
        wishListRepository.removeWishlistItem(userId, productId);
    }
}
