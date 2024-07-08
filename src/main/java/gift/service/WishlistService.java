package gift.service;

import gift.dto.request.WishlistNameRequest;
import gift.domain.WishlistItem;
import gift.exception.MemberNotFoundException;
import gift.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public void addItemToWishlist(WishlistNameRequest wishlistNameRequest) {
        WishlistItem item = new WishlistItem(wishlistNameRequest.getMemberId(), wishlistNameRequest.getItemName());
        wishlistRepository.addItem(item);
    }

    public void deleteItemFromWishlist(Long itemId, Long memberId) {
        boolean itemExists = wishlistRepository.getItemsByMemberId(memberId)
                .stream()
                .anyMatch(item -> item.getId().equals(itemId));

        if (!itemExists) {
            throw new MemberNotFoundException("해당 아이템이 존재하지 않습니다: " + itemId);
        }

        wishlistRepository.deleteItem(itemId);
    }

    public List<WishlistItem> getWishlistByMemberId(Long memberId) {
        return wishlistRepository.getItemsByMemberId(memberId);
    }
}
