package gift.service;

import gift.dto.request.WishlistIdRequest;
import gift.dto.request.WishlistNameRequest;
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
        WishlistItem item = new WishlistItem(wishlistNameRequest.getMemberId(), wishlistNameRequest.getItemName());
        wishlistRepository.addItem(item);
    }

    public void deleteItemFromWishlist(WishlistIdRequest wishlistIdRequest) {
        WishlistItem existingItem = wishlistRepository.getItemsByMemberId(wishlistIdRequest.getMemberId())
                .stream()
                .filter(item -> item.getId().equals(wishlistIdRequest.getItemId()))
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundException("위시리스트가 비어있습니다: " + wishlistIdRequest.getMemberId()));

        if (!existingItem.getMemberId().equals(wishlistIdRequest.getMemberId())) {
            throw new AccessDeniedException("아이템 삭제 권한이 없습니다.");
        }

        wishlistRepository.deleteItem(wishlistIdRequest.getItemId());
    }

    public List<WishlistItem> getWishlistByMemberId(Long memberId) {
        return wishlistRepository.getItemsByMemberId(memberId);
    }
}
