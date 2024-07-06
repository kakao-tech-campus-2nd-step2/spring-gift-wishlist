package gift.service;

import gift.dao.WishlistDao;
import gift.vo.WishProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private WishlistDao wishlistDao;

    public WishlistService(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    public List<WishProduct> getWishProductLost(String memberId) {
        return wishlistDao.getWishProductList(memberId);
    }
}
