package gift.wishlist.controller;

import gift.member.error.UnauthorizedException;
import gift.member.util.JwtUtil;
import gift.wishlist.domain.WishList;
import gift.wishlist.service.WishListService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<WishList>> getWishListItems(HttpServletRequest request) {
        String token = extractToken(request);
        Claims claims = jwtUtil.extractAllClaims(token);
        Long memberId = Long.valueOf(claims.getSubject());

        List<WishList> items = wishListService.getWishListItems(memberId);
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<Void> addWishListItem(HttpServletRequest request, @RequestBody WishList item) {
        String token = extractToken(request);
        Claims claims = jwtUtil.extractAllClaims(token);
        Long memberId = Long.valueOf(claims.getSubject());

        item.setMemberId(memberId);
        wishListService.addWishListItem(item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishListItem(@PathVariable Long id) {
        wishListService.deleteWishListItem(id);
        return ResponseEntity.ok().build();
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new UnauthorizedException("Invalid token");
    }

}
