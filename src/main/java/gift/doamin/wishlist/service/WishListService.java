package gift.doamin.wishlist.service;

import gift.doamin.wishlist.dto.WishListForm;
import gift.doamin.wishlist.entity.WishList;
import gift.doamin.wishlist.repository.WishListRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public WishList create(Long userId, WishListForm wishListForm) {
        var wishList = new WishList(userId, wishListForm.getProductId(), wishListForm.getQuantity());
        return wishListRepository.save(wishList);
    }

    public List<WishList> read(Long userId) {
        return wishListRepository.findByUserId(userId);
    }

}
