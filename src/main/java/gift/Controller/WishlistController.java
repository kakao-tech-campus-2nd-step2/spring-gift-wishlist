package gift.Controller;

import gift.Exception.AuthorizedException;
import gift.Exception.LoginException;
import gift.Model.Product;
import gift.Model.Wishlist;
import gift.Service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService){
        this.wishlistService = wishlistService;
    }

    @PostMapping("/add")
    public void addWishlist(@RequestHeader("bearer") String token, @RequestBody Product product){
        wishlistService.add(token, product);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteWishlist(@RequestHeader("bearer") String token, @PathVariable String name){
        wishlistService.delete(token, name);
    }

    @GetMapping()
    public List<Wishlist> viewAllWishlist(@RequestHeader("bearer") String token){
        return wishlistService.viewAll(token);
    }

    @ExceptionHandler(AuthorizedException.class)
    public ResponseEntity<?> Authorized(LoginException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
