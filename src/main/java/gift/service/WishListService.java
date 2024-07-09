package gift.service;

import gift.model.Product;
import gift.model.WishList;
import gift.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    public WishList getWishListByUsername(String username) {
        return wishListRepository.findByUsername(username)
                .orElse(new WishList(username));
    }

    public void addProductToWishList(String username, Product product) {
        WishList wishList = getWishListByUsername(username);
        wishList.getProducts().add(product);
        wishListRepository.save(wishList);
    }

    public void removeProductFromWishList(String username, Long productId) {
        WishList wishList = getWishListByUsername(username);
        wishList.getProducts().removeIf(product -> product.getId().equals(productId));
        wishListRepository.save(wishList);
    }
}
