package gift.controller;

import gift.domain.WishlistItem;
import gift.dto.request.WishlistIdRequest;
import gift.dto.request.WishlistNameRequest;
import gift.service.WishlistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/wishlist/add")
    public void addToWishlist(@Valid @RequestBody WishlistNameRequest request) {
        wishlistService.addItemToWishlist(request);
    }

    @DeleteMapping("/wishlist/delete")
    public void deleteFromWishlist(@Valid @RequestBody WishlistIdRequest request) {
        wishlistService.deleteItemFromWishlist(request);
    }

    @GetMapping("/wishlist/get/{memberId}")
    public List<WishlistItem> getWishlist(@PathVariable Long memberId) {
        return wishlistService.getWishlistByMemberId(memberId);
    }
}
