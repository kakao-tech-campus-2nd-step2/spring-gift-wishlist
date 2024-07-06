package gift.controller;

import gift.Entity.LoginUser;
import gift.Entity.Wish;
import gift.service.LoginMember;
import gift.service.WishService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishes")
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping("/{productId}")
    public void create(@PathVariable("productId") long productId, @LoginMember LoginUser loginUser) {
        System.out.println("post");
        wishService.addWish(productId, loginUser);
    }


    @GetMapping
    public List<Wish> getWishes(@LoginMember LoginUser loginUser) {
        System.out.println("get");
        return wishService.getWishesByMemberId(loginUser);
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable("productId") long productId, @LoginMember LoginUser loginUser) {
        System.out.println("delete");
        wishService.removeWish(productId, loginUser);
    }


}
