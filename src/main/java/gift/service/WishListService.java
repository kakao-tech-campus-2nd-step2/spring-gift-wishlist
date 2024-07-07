package gift.service;

import gift.model.Product;
import gift.model.WishList;
import gift.repository.WishListRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
    @Autowired
    WishListRepository wishListRepository;

    public List<WishList> readWishList(Long userId) {
        return wishListRepository.readWishList(userId);
    }

    public void addProductToWishList(Long userid, Product product) {
        wishListRepository.addProductToWishList(userid, product);
    }

    public void removeWishList(Long id) {
        wishListRepository.removeWishList(id);
    }

    public void removeProductFromWishList(Long userid, Long wishId) {
        wishListRepository.removeProductFromWishList(userid, wishId);
    }

}
