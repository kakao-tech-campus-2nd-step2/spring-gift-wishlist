package wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    @Autowired
    private final WishlistService wishlistService;
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public ResponseEntity<List<WishilistItem>> getAllWishlist(@RequestHeader String token) {
        return ResponseEntity.ok(wishlistService.getAllWishlist(token));
    }

    @PostMapping
    public ResponseEntity<String> addWishlist(@RequestHeader String token, @RequestBody long id) {
        wishlistService.addItem(token, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteWishlist(@RequestHeader String token, @RequestBody long id) {
        try {
            wishlistService.deleteItem(token, id);
            return ResponseEntity.ok("정상적으로 삭제되었습니다.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> updateWishlist(@RequestHeader String token, @RequestBody long id, @RequestBody long num) {
        try {
            wishlistService.changeNum(token, id, num);
            return ResponseEntity.ok("상품 수량이 변경되었습니다.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
