package gift.service;

import gift.controller.WishlistIdRequest;
import gift.controller.WishlistNameRequest;
import gift.domain.WishlistItem;
import gift.exception.AccessDeniedException;
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
        WishlistItem item = new WishlistItem(null, wishlistNameRequest.getMemberId(), wishlistNameRequest.getItemName());
        wishlistRepository.addItem(item);
    }

    public void deleteItemFromWishlist(WishlistIdRequest wishlistIdRequest) {
        WishlistItem existingItem = wishlistRepository.getItemsByMemberId(wishlistIdRequest.getMemberId())
                .stream()
                .filter(item -> item.getId().equals(wishlistIdRequest.getItemId()))
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundException("Wishlist item not found for memberId: " + wishlistIdRequest.getMemberId()));

        if (!existingItem.getMemberId().equals(wishlistIdRequest.getMemberId())) {
            throw new AccessDeniedException("You are not authorized to delete this item.");
        }

        wishlistRepository.deleteItem(wishlistIdRequest.getItemId());
    }

    public List<WishlistItem> getWishlistByMemberId(Long memberId) {
        return wishlistRepository.getItemsByMemberId(memberId);
    }
}
