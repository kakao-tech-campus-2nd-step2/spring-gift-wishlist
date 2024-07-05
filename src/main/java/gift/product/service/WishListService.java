package gift.product.service;

import gift.product.dao.WishListDao;
import gift.product.model.Product;
import gift.product.model.WishProduct;
import java.util.Collection;
import java.util.Map;

import gift.product.model.WishProduct2;
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

    public Collection<WishProduct2> getAllProducts(String email) {
        return wishListDao.getAllProducts(email);
    }

    public void registerWishProduct(WishProduct wProduct) {
        wishListDao.registerWishProduct(wProduct);
    }

    public void updateCountWishProduct(Long pId, int count, String email) {
        wishListDao.updateCountWishProduct(pId, count, email);
    }

    public void deleteWishProduct(Long pId, String email) {
        wishListDao.deleteWishProduct(pId, email);
    }

    public boolean existsByPId(Long pId, String email) {
        return wishListDao.existsByPId(pId, email);
    }
}
