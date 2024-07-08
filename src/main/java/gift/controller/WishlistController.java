package gift.controller;

import gift.domain.WishlistItem;
import gift.dto.request.WishlistNameRequest;
import gift.service.WishlistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    public void addToWishlist(@Valid @RequestBody WishlistNameRequest request) {
        wishlistService.addItemToWishlist(request);
    }

    @DeleteMapping("/{itemId}")
    public void deleteFromWishlist(@PathVariable Long itemId, @RequestParam Long memberId) {
        wishlistService.deleteItemFromWishlist(itemId, memberId);
    }

    @GetMapping("/{memberId}")
    public List<WishlistItem> getWishlist(@PathVariable Long memberId) {
        return wishlistService.getWishlistByMemberId(memberId);
    }
}
