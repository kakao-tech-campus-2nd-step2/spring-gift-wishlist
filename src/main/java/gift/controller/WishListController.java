package gift.controller;

import gift.model.WishListItem;
import gift.service.WishListService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping
    public List<WishListItem> getWishList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = (Long) authentication.getPrincipal();
        return wishListService.getWishListByMemberId(memberId);
    }

    @PostMapping
    public void addWishListItem(@RequestBody WishListItem item) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = (Long) authentication.getPrincipal();
        item.setMemberId(memberId);
        wishListService.addWishListItem(item);
    }

    @DeleteMapping("/{id}")
    public void removeWishListItem(@PathVariable Long id) {
        wishListService.removeWishListItem(id);
    }
}
