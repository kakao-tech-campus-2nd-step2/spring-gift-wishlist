package gift.controller.wish;

import gift.auth.Auth;
import gift.auth.GetLoginInfo;
import gift.auth.LoginInfo;
import gift.controller.wish.dto.WishRequest.DeleteWishRequest;
import gift.controller.wish.dto.WishRequest.AddWishRequest;
import gift.controller.wish.dto.WishRequest.UpdateWishRequest;
import gift.controller.wish.dto.WishResponse.WishListResponse;
import gift.model.user.Role;
import gift.model.wish.WishDao;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishes")
public class WishController {

    private final WishDao wishDao;

    public WishController(WishDao wishDao) {
        this.wishDao = wishDao;
    }

    @Auth(role = Role.USER)
    @PostMapping("")
    public ResponseEntity<String> addWish(
        @GetLoginInfo LoginInfo loginInfo,
        @Valid @RequestBody AddWishRequest request
    ) {
        var wish = wishDao.findByProductIdAndUserId(request.productId(), loginInfo.userId());
        if (wish.isPresent()) {
            throw new IllegalArgumentException("Wish already exists.");
        }
        wishDao.insert(request.toEntity(loginInfo.userId()));
        return ResponseEntity.ok().body("Wish insert successfully.");
    }

    @Auth(role = Role.USER)
    @DeleteMapping("")
    public ResponseEntity<String> deleteWish(
        @GetLoginInfo LoginInfo loginInfo,
        @RequestBody @Valid DeleteWishRequest request
    ) {
        var wish = wishDao.findByProductIdAndUserId(request.productId(), loginInfo.userId())
            .orElseThrow(() -> new IllegalArgumentException("Wish not found."));
        wishDao.deleteById(wish.getId());
        return ResponseEntity.ok().body("Wish removed successfully.");
    }

    @Auth(role = Role.USER)
    @GetMapping("")
    public ResponseEntity<WishListResponse> getWishes(@GetLoginInfo LoginInfo loginInfo) {
        return ResponseEntity.ok(WishListResponse.from(wishDao.findAll(loginInfo.userId())));
    }

    @Auth(role = Role.USER)
    @PatchMapping("")
    public ResponseEntity<String> updateWish(
        @GetLoginInfo LoginInfo loginInfo,
        @Valid @RequestBody UpdateWishRequest request) {
        var wish = wishDao.findByProductIdAndUserId(request.productId(), loginInfo.userId())
            .orElseThrow(() -> new IllegalArgumentException("Wish not found."));
        wishDao.updateCount(loginInfo.userId(), wish.getId(), request.count());
        return ResponseEntity.ok().body("Wish updated successfully.");
    }
}
