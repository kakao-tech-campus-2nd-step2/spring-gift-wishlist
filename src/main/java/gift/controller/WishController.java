package gift.controller;

import gift.domain.Wish;
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

    @PostMapping
    public void addWish(@RequestParam Long memberId, @RequestParam Long productId) {
        wishService.addWish(memberId, productId);
    }

    @GetMapping
    public List<Wish> getWishes(@RequestParam Long memberId) {
        return wishService.getWishes(memberId);
    }

    @DeleteMapping
    public void removeWish(@RequestParam Long memberId, @RequestParam Long productId) {
        wishService.removeWish(memberId, productId);
    }
}
