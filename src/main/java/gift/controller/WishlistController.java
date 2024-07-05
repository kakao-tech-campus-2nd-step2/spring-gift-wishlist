package gift.controller;

import gift.annotation.LoginUser;
import gift.domain.ProductWithQuantity;
import gift.domain.Wishlist;
import gift.service.WishlistService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService){
        this.wishlistService=wishlistService;
    }
    @GetMapping("/{email}")
    public List<Wishlist> getWishlist(@PathVariable("email") String email){
        return wishlistService.getWishlist(email);
    }

    @PostMapping
    public ResponseEntity<String> addWishlist(@RequestBody ProductWithQuantity productWithQuantity, @LoginUser String email){
        wishlistService.addWishlist(productWithQuantity,email);
        return new ResponseEntity<>("성공", HttpStatus.OK);
    }
}
