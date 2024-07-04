package gift.product.service;

import gift.product.dao.WishListDao;
import gift.product.model.Product;
import gift.product.model.WishProduct;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
    private final WishListDao wishListDao;

    @Autowired
    public WishListService(WishListDao wishListDao) {
        this.wishListDao = wishListDao;
        wishListDao.createWishListTable();
    }

    public Collection<Product> getAllProducts(String email) {
        return wishListDao.getAllProducts(email);
    }

    public void registerWishProduct(WishProduct wProduct) {
        wishListDao.registerWishProduct(wProduct);
    }

    public void updateCountWishProduct(Long id, int count) {
        wishListDao.updateCountWishProduct(id, count);
    }

    public void deleteWishProduct(Long id) {
        wishListDao.deleteWishProduct(id);
    }
}
