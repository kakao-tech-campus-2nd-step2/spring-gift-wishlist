package gift.controller;

import gift.common.annotation.LoginUser;
import gift.model.product.ProductResponse;
import gift.model.user.User;
import gift.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/wish")
public class WishListController {

    private final UserService userService;

    public WishListController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllWishList(@LoginUser User user) {
        return userService.findWishList(user.getId());
    }
}
