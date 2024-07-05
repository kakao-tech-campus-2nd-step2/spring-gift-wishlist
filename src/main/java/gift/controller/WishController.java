package gift.controller;

import gift.domain.Product;
import gift.service.WishService;
import gift.utils.JwtTokenProvider;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wish")
public class WishController {

    private final WishService wishlistService;
    private final JwtTokenProvider jwtTokenProvider;

    public WishController(WishService wishlistService, JwtTokenProvider jwtTokenProvider) {
        this.wishlistService = wishlistService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<String> addToWishlist(@PathVariable Long productId,
        @RequestHeader("Authorization") String token) {
        String email = jwtTokenProvider.getEmailFromToken(token.substring(7));
        wishlistService.addToWishlist(email, productId);
        return ResponseEntity.ok("Product added to wishlist");

    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeFromWishlist(@PathVariable Long productId,
        @RequestHeader("Authorization") String token) {
        String email = jwtTokenProvider.getEmailFromToken(token.substring(7));
        wishlistService.removeFromWishlist(email, productId);
        return ResponseEntity.ok("Product removed from wishlist");

    }

    @GetMapping
    public ResponseEntity<List<Product>> getWishlist(@RequestHeader("Authorization") String token) {
        String email = jwtTokenProvider.getEmailFromToken(token.substring(7));
        List<Product> wishlistProducts = wishlistService.getWishlistProducts(email);
        return ResponseEntity.ok(wishlistProducts);
    }

}
