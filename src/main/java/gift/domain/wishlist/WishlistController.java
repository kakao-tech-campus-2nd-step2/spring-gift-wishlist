package gift.domain.wishlist;

import gift.domain.user.User;
import gift.domain.user.annotation.ValidUser;
import gift.domain.wishlist.dto.WishlistDeleteRequestDto;
import gift.domain.wishlist.dto.WishlistDto;
import gift.global.response.SuccessResponse;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishes")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getWishlist(@ValidUser User user) {
        return SuccessResponse.ok(wishlistService.getWishlist(user), "wishlist");
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addWishlist(@ValidUser User user, @Valid @RequestBody WishlistDto wishlistDto) {
        return SuccessResponse.ok(wishlistService.addWishlist(user, wishlistDto), "result");
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateWishlist(@ValidUser User user, @Valid @RequestBody WishlistDto wishlistDto) {
        return SuccessResponse.ok(wishlistService.updateWishlist(user, wishlistDto), "result");
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteWishlist(@ValidUser User user, @RequestBody WishlistDeleteRequestDto deleteRequestDto) {
        wishlistService.deleteWishlist(user, deleteRequestDto);
        return SuccessResponse.of(HttpStatus.NO_CONTENT);
    }
}
