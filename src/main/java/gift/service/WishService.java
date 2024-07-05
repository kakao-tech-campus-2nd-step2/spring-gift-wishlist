package gift.service;

import gift.domain.Product;
import gift.repository.WishsRepository;
import gift.utils.error.WishListAddFailedException;
import gift.utils.error.WishListNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishService {

    private final WishsRepository wishsRepository;

    public WishService(WishsRepository wishsRepository) {
        this.wishsRepository = wishsRepository;
    }

    public boolean addToWishlist(String email, Long productId) {
        try {
            return wishsRepository.addToWishlist(email, productId);
        } catch (Exception e) {
            throw new WishListAddFailedException("Add Failed");
        }

    }

    public boolean removeFromWishlist(String email, Long productId) {
        try {
            return wishsRepository.removeFromWishlist(email, productId);
        } catch (Exception e) {
            throw new WishListNotFoundException("Not Found");
        }

    }

    public List<Product> getWishlistProducts(String email) {
        return wishsRepository.getWishlistProducts(email);
    }

}
