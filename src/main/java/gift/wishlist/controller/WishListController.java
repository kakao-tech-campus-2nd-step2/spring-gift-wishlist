package gift.wishlist.controller;

import gift.user.model.dto.User;
import gift.user.resolver.LoginUser;
import gift.wishlist.model.dto.WishListResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishes")
public class WishListController {
    @GetMapping("")
    public List<WishListResponse> getWishListForUser(@LoginUser User loginUser) {
        return wishListRepository.getWishList(loginUser.id());
    }
}
