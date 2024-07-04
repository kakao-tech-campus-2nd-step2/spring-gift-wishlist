package gift.service;

import gift.model.WishList;
import gift.model.WishListDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;
    private static final Logger logger = LoggerFactory.getLogger(WishListService.class);

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public void createWishList(String email, WishListDTO wishListDTO) {
        wishListRepository.createWishList(wishListDTO);
    }

    public List<WishList> getAllWishList() {
        return wishListRepository.getAllWishList();
    }

    public List<WishList> getWishListByEmail(String email) {
        logger.info("Fetching wish list for email: {}", email);
        return wishListRepository.getWishListByEmail(email);
    }

    public void deleteWishList(String email, String product_name) {
        wishListRepository.deleteWishList(email, product_name);
    }
}
