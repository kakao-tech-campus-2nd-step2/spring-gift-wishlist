package gift.controller;

import gift.model.WishList;
import gift.service.WishListService;
import gift.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    private final WishListService wishListService;
    private final JwtUtil jwtUtil;

    public WishListController(WishListService wishListService, JwtUtil jwtUtil) {
        this.wishListService = wishListService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<?> getWishList(@RequestHeader("Authorization") String token) {
        Long memberId = jwtUtil.extractMemberId(token);
        List<WishList> wishList = wishListService.getWishListByMemberId(memberId);
        return ResponseEntity.ok(wishList);
    }

    @PostMapping
    public ResponseEntity<?> addProductToWishList(@RequestHeader("Authorization") String token, @RequestParam Long productId) {
        Long memberId = jwtUtil.extractMemberId(token);
        WishList wishList = wishListService.addProductToWishList(memberId, productId);
        return ResponseEntity.ok(wishList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProductFromWishList(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        wishListService.removeProductFromWishList(id);
        return ResponseEntity.noContent().build();
    }
}
