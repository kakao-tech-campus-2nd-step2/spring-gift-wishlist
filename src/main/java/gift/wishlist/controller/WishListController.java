package gift.wishlist.controller;

import gift.user.model.dto.User;
import gift.user.resolver.LoginUser;
import gift.wishlist.model.dto.AddWishRequest;
import gift.wishlist.model.dto.WishListResponse;
import gift.wishlist.service.WishListService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishes")
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("")
    public List<WishListResponse> getWishListForUser(@LoginUser User loginUser) {
        return wishListService.getWishList(loginUser.id());
    }

    @GetMapping("/admin/{userId}")
    public List<WishListResponse> getWishListForAdmin(@LoginUser User loginUser, @PathVariable("userId") Long userId) {
        wishListService.verifyAdminAccess(loginUser);
        return wishListService.getWishList(userId);
    }

    @PostMapping("")
    public void addWish(@LoginUser User loginUser, @RequestBody AddWishRequest addWishRequest) {
        wishListService.addWish(loginUser.id(), addWishRequest);
    }

    @PostMapping("/admin/{userId}")
    public void addWishForAdmin(@LoginUser User loginUser, @PathVariable("userId") Long userId,
                                @RequestBody AddWishRequest addWishRequest) {
        wishListService.verifyAdminAccess(loginUser);
        wishListService.addWish(userId, addWishRequest);
    }

    @PatchMapping("/{wishId}")
    public void updateWishQuantity(@LoginUser User loginUser, @PathVariable("wishId") Long wishId,
                                   @RequestParam int quantity) {
        wishListService.updateWishQuantity(loginUser.id(), wishId, quantity);
    }

    @PatchMapping("/admin/{userId}/{wishId}")
    public void updateWishQuantityForAdmin(@LoginUser User loginUser, @PathVariable("userId") Long userId,
                                           @PathVariable("wishId") Long wishId,
                                           @RequestParam int quantity) {
        wishListService.verifyAdminAccess(loginUser);
        wishListService.updateWishQuantity(userId, wishId, quantity);
    }

    @DeleteMapping("/{wishId}")
    public void deleteWish(@LoginUser User loginUser, @PathVariable("wishId") Long wishId) {
        wishListService.deleteWish(loginUser.id(), wishId);
    }

    @DeleteMapping("/admin/{userId}/{wishId}")
    public void deleteWishForAdmin(@LoginUser User loginUser, @PathVariable("userId") Long userId,
                                   @PathVariable("wishId") Long wishId) {
        wishListService.verifyAdminAccess(loginUser);
        wishListService.deleteWish(userId, wishId);
    }
}
