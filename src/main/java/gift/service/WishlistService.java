package gift.service;

import gift.dao.ProductWithQuantityDao;
import gift.dao.WishlistDao;
import gift.domain.ProductWithQuantity;
import gift.domain.Wishlist;
import java.util.List;
import org.springframework.stereotype.Service;

@Service

public class WishlistService {

    private final ProductWithQuantityDao productWithQuantityDao;
    private final WishlistDao wishlistDao;

    public WishlistService(ProductWithQuantityDao productWithQuantityDao, WishlistDao wishlistDao) {
        this.productWithQuantityDao = productWithQuantityDao;
        this.wishlistDao = wishlistDao;
        wishlistDao.createWishlistTable();
        productWithQuantityDao.createProductWithQuantityTable();
    }

    public void addWishlist(ProductWithQuantity productWithQuantity, String email) {
        if (wishlistDao.getWishlistId(email) == 0L) {
            wishlistDao.addWishlist(email);
        }
        long wishlistId = wishlistDao.getWishlistId(email);

        productWithQuantityDao.saveProductWithQuantity(productWithQuantity.getProductId(),
            productWithQuantity.getQuantity(), wishlistId);
    }

    public List<Wishlist> getWishlist(String email) {
        return wishlistDao.findAllWishlist(email);
    }


}
