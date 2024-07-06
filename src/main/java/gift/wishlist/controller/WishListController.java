package gift.wishlist.controller;

import gift.user.model.dto.User;
import gift.user.resolver.LoginUser;
import gift.wishlist.model.dto.WishListResponse;
import gift.wishlist.service.WishListService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
