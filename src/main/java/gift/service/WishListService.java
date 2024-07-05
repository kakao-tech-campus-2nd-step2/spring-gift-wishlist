package gift.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    public List<Product> getWishList(Long userId) {
        return wishListRepository.findByUserId(userId);
    }

    public void addProductToWishList(Long userId, Product product) {

    }

    public void removeProductFromWishList(Long userId, Long productId) {
    }
}