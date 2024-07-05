package gift.service;

import gift.model.WishListItem;
import gift.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public void addWishListItem(WishListItem item) {
        wishListRepository.addWishListItem(item);
    }

    public void removeWishListItem(Long id) {
        wishListRepository.removeWishListItem(id);
    }

    public List<WishListItem> getWishListByMemberId(Long memberId) {
        return wishListRepository.findWishListByMemberId(memberId);
    }
}
