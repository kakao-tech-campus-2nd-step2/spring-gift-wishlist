package gift.wishlist.service;

import gift.wishlist.domain.WishList;
import gift.wishlist.repository.WishListRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public List<WishList> getWishListItems(Long memberId) {
        return wishListRepository.findByMemberId(memberId);
    }

    public void addWishListItem(WishList item) {
        wishListRepository.addWishListItem(item);
    }

    public void deleteWishListItem(Long id) {
        wishListRepository.deleteWishListItem(id);
    }

}
