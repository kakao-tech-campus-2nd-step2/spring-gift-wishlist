package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
}
