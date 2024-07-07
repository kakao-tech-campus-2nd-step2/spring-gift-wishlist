package gift.controller;

import gift.dto.WishDto;
import gift.model.wish.Wish;
import gift.service.WishListService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping
    public ResponseEntity<List<Wish>> getAllWishes() {
        List<Wish> wishes = wishListService.getAllWishes();
        return ResponseEntity.ok(wishes);
    }

    @PostMapping
    public ResponseEntity<Void> insertWish(@RequestBody WishDto wishDto) {
        wishListService.insertWish(wishDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeWish(@PathVariable Long id) {
        wishListService.deleteWish(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateWish(@RequestBody WishDto wishDto) {
        wishListService.updateWish(wishDto);
        return ResponseEntity.ok().build();
    }
}