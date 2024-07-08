package gift.service;

import gift.dao.WishlistDao;
import gift.vo.WishProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistDao wishlistDao;

    public WishlistService(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    public List<WishProduct> getWishProductLost(String memberId) {
        return wishlistDao.getWishProductList(memberId);
    }

    public Boolean addWishProduct(WishProduct wishProduct) {
        return wishlistDao.addWishProduct(wishProduct.getMemberId(), wishProduct.getProductId());
    }

    public Boolean deleteWishProduct(String memberId, Long productId) {
        return wishlistDao.deleteWishProduct(memberId, productId);
    }

}
