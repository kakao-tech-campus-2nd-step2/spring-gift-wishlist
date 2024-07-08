package gift.controller;

import gift.service.JwtUtil;
import gift.service.WishlistService;
import gift.vo.WishProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static gift.service.JwtUtil.getBearerToken;

@RestController
public class WishlistController {

    private final WishlistService service;
    private final JwtUtil jwtUtil;

    public WishlistController(WishlistService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/wishlist")
    public List<WishProduct> getWishProductList(@RequestHeader("Authorization") String authorizationHeader) {
        String token = getBearerToken(authorizationHeader);

        String memberId = jwtUtil.getMemberEmailFromToken(token);
        return service.getWishProductLost(memberId);
    }

    @PostMapping("/wishlist/add/{productId}")
    public ResponseEntity<Void> addToWishlist(@PathVariable("productId") Long productId, @RequestHeader("Authorization") String authorizationHeader) {
        String token = getBearerToken(authorizationHeader);
        String memberId = jwtUtil.getMemberEmailFromToken(token);

        WishProduct wishProduct = new WishProduct(memberId, productId);
        Boolean result = service.addWishProduct(wishProduct);
        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/wishlist/{wishProductId}")
    public ResponseEntity<Void> deleteToWishlist(@PathVariable("wishProductId") Long wishProductId, @RequestHeader("Authorization") String authorizationHeader) {
        String token = getBearerToken(authorizationHeader);
        String memberId = jwtUtil.getMemberEmailFromToken(token);

        Boolean result = service.deleteWishProduct(memberId, wishProductId);
        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
