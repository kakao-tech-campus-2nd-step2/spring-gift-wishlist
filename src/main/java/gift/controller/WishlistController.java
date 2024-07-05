package gift.controller;

import gift.DTO.Product;
import gift.service.UserService;
import gift.service.WishlistService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final UserService userService;
    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(UserService us, WishlistService ws) {
        userService = us;
        wishlistService = ws;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getWishlist(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7); // "Bearer " 부분을 제거
        if (!userService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String email = userService.extractEmailFromToken(token);
        List<Product> wishlist = wishlistService.getWishlistByEmail(email);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping
    public ResponseEntity<String> addToWishlist(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Long productId) {
        String token = authorizationHeader.substring(7); // "Bearer " 부분을 제거
        if (!userService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        String email = userService.extractEmailFromToken(token);
        wishlistService.addWishlist(email, productId);
        return ResponseEntity.ok("Product added to wishlist");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> removeFromWishlist(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long productId) {
        String token = authorizationHeader.substring(7); // "Bearer " 부분 제거
        if (!userService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        String email = userService.extractEmailFromToken(token);
        wishlistService.removeWishlist(email, productId);
        return ResponseEntity.ok("Product removed from wishlist");
    }
}