package gift;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {
    private final WishListService wishListService;
    private final JwtUtil jwtUtil;

    @Autowired
    public WishListController(WishListService wishListService, JwtUtil jwtUtil) {
        this.wishListService = wishListService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<List<WishList>> getWishList(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        String email = jwtUtil.extractEmail(authHeader);
        List<WishList> wishList = wishListService.getWishList(email);
        return ResponseEntity.ok(wishList);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProductToWishList(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @RequestBody @Valid Product product) {
        String email = jwtUtil.extractEmail(authHeader);
        wishListService.addProductToWishList(email, product.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Void> removeProductFromWishList(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @PathVariable Long productId) {
        String email = jwtUtil.extractEmail(authHeader);
        wishListService.removeProductFromWishList(email, productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
