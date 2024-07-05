package gift.controller;

import gift.annotation.LoginUser;
import gift.dto.user.WishProductDto;
import gift.service.WishlistService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/wishes")
public class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public ResponseEntity<List<WishProductDto>> getWishlist(@LoginUser Long userId) {
        return new ResponseEntity<>(wishlistService.getWishlist(userId), HttpStatus.OK);
    }


}
