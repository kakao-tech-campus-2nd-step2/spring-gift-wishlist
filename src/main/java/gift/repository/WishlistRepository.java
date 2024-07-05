package gift.repository;

import gift.model.WishListDAO;
import gift.model.WishListDTO;

import java.util.List;

public interface WishlistRepository {
    boolean addWishlist(String email, WishListDTO wishlist);

    boolean removeWishlist(String email, Long productId);

    boolean updateWishlist(String email, WishListDTO wishlist);

    List<WishListDAO> getMyWishlists(String email);
}
