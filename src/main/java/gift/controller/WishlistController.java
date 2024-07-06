package gift.controller;

import gift.service.JwtUtil;
import gift.service.WishlistService;
import gift.vo.WishProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/wishlist/add")
    public ResponseEntity<Void> addToWishlist(@RequestParam("productId") Long productId, @RequestHeader("Authorization") String authorizationHeader) {
        String token = getBearerToken(authorizationHeader);
        String memberId = jwtUtil.getMemberEmailFromToken(token);

        WishProduct wishProduct = new WishProduct(memberId, productId);
        Boolean result = service.addWishProduct(wishProduct);
        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/wishlist/delete")
    public ResponseEntity<Void> deleteToWishlist(@RequestParam("productId") Long productId, @RequestHeader("Authorization") String authorizationHeader) {
        String token = getBearerToken(authorizationHeader);
        String memberId = jwtUtil.getMemberEmailFromToken(token);

        Boolean result = service.deleteWishProduct(memberId, productId);
        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     *
     * @param authorizationHeader Authorization 헤더
     * @return Bearer 토큰 추출
     */
    private static String getBearerToken(String authorizationHeader) {
        return authorizationHeader.replace("Bearer ", "");
    }
}
