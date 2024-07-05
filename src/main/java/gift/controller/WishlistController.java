package gift.controller;

import gift.annotation.LoginUser;
import gift.dto.wishlist.WishProductDto;
import gift.dto.wishlist.WishRequestDto;
import gift.service.WishlistService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ResponseEntity<List<WishProductDto>> addWishlist(
        @LoginUser Long userId, @RequestBody WishRequestDto wishRequest
    ) {
        return new ResponseEntity<>(wishlistService.addWishlist(userId, wishRequest), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<List<WishProductDto>> updateWishlist(
        @LoginUser Long userId, @RequestBody List<WishRequestDto> wishRequests
    ) {
        return new ResponseEntity<>(wishlistService.updateWishlist(userId, wishRequests), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<List<WishProductDto>> deleteWishlist(
        @LoginUser Long userId, @RequestBody List<WishRequestDto> wishRequests
    ) {
        return new ResponseEntity<>(wishlistService.deleteWishlist(userId, wishRequests), HttpStatus.OK);
    }
}
