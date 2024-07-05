package gift.service;

import gift.model.WishList;
import gift.repository.WishListRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public List<WishList> getWishListByMemberId(Long memberId) {
        return wishListRepository.findByMemberId(memberId);
    }

    public WishList addProductToWishList(Long memberId, Long productId) {
        return wishListRepository.addWishListItem(new WishList(null, memberId, productId));
    }

    public void removeProductFromWishList(Long wishListId) {
        wishListRepository.deleteById(wishListId);
    }
}
