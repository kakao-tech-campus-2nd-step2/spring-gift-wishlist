package gift.doamin.wishlist.controller;

import gift.doamin.wishlist.dto.WishListForm;
import gift.doamin.wishlist.entity.WishList;
import gift.doamin.wishlist.repository.WishListRepository;
import java.security.Principal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    private final WishListRepository wishListRepository;

    public WishListController(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    @PostMapping
    public void createWishList(@RequestBody WishListForm wishListForm, Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        WishList wishList = new WishList(userId,wishListForm.getProductId(), wishListForm.getQuantity());

        wishListRepository.save(wishList);
    }

    @GetMapping
    public List<WishList> getWishList(Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        System.out.println("userId = " + userId);
        System.out.println(wishListRepository.findByUserId(userId));

        return wishListRepository.findByUserId(userId);
    }
}
