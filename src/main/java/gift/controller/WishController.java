package gift.controller;

import gift.domain.Wish;
import gift.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wishes")
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping
    @ResponseBody
    public void addWish(@RequestBody Wish wish) {
        wishService.addWish(wish.getMemberId(), wish.getProductId());
    }

    @GetMapping
    public String getWishes(@RequestParam Long memberId, Model model) {
        List<Wish> wishes = wishService.getWishes(memberId);
        model.addAttribute("wishes", wishes);
        model.addAttribute("memberId", memberId);
        return "wishlist";
    }

    @DeleteMapping("/remove")
    @ResponseBody
    public void removeWish(@RequestBody Wish wish) {
        wishService.removeWish(wish.getMemberId(), wish.getProductId());
    }
}
