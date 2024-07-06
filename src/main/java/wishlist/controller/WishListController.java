package wishlist.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import wishlist.model.item.ItemDTO;
import wishlist.service.UserService;
import wishlist.service.WishListService;

@RestController
public class WishListController {
    private final WishListService wishListService;
    private final UserService userService;
    public WishListController(WishListService wishListService,UserService userService) {
        this.wishListService = wishListService;
        this.userService = userService;
    }

    @GetMapping("/wishes")
    public List<ItemDTO> getWishList(@RequestAttribute("email") String email) {
        return wishListService.getList(userService.findByEmail(email).getId());
    }

    @GetMapping("/wishes/add/{id}")
    public void addToWishList(@PathVariable("id") Long itemId ,@RequestAttribute("email") String email) {
        wishListService.addToWishList(userService.findByEmail(email).getId(), itemId);
    }

    @DeleteMapping("/wishes/{id}")
    public void deleteFromWishList(@PathVariable("id") Long itemId, @RequestAttribute("email") String email) {
        wishListService.deleteFromWishList(userService.findByEmail(email).getId(), itemId);
    }
}