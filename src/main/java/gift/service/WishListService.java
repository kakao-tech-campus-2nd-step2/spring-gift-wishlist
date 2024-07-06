package gift.service;

import gift.dao.WishDao;
import gift.dto.WishDto;
import gift.model.wish.Wish;

import java.util.List;

public class WishListService {
    private final WishDao wishDao;
    private final ProductService productService;

    public WishListService(WishDao wishDao, ProductService productService){
        this.wishDao = wishDao;
        this.productService = productService;
    }

    public List<Wish> getAllWishes() {
        return wishDao.getAllWishes();
    }

    public void insertWish(WishDto wishDto) {
        Wish wish = new Wish(wishDto.getProductId(),wishDto.getMemberEmail(),wishDto.getProductName(), wishDto.getAmount());
        wishDao.insertWish(wish);
    }

    public void deleteWish(Long productId) {
        wishDao.deleteWish(productId);
    }

    public void updateWish(WishDto wishDto){
        Wish wish = new Wish(wishDto.getProductId(),wishDto.getMemberEmail(),wishDto.getProductName(), wishDto.getAmount());
        wishDao.updateWish(wish);
    }
}
