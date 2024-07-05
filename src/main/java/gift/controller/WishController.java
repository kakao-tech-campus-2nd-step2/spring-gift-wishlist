package gift.controller;

import gift.dto.WishRequest;
import gift.model.LoginUser;
import gift.model.Product;
import gift.model.User;
import gift.service.WishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping("/wishes")
    public ResponseEntity makeWish(@RequestBody WishRequest request, @LoginUser User user) {
        wishService.makeWish(request, user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/wishes")
    public ResponseEntity<List<Product>> getAllWishProductsByUser(@LoginUser User user) {
        List<Product> allProducts = wishService.getAllWishProductsByUser(user.getEmail());
        return ResponseEntity.ok().body(allProducts);
    }
}
