package gift.wishlist;

import gift.member.Member;
import gift.member.MemberResolver;
import gift.product.Product;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishes")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public List<Product> getAllWishlists(@Valid @MemberResolver Member member) {
        return wishlistService.getAllWishlists(member);
    }

    @PostMapping("/{product_id}")
    public void addWishlist(@Valid @MemberResolver Member member,
        @PathVariable(name = "product_id") long productId) {
        wishlistService.addWishlist(member, productId);
    }

    @DeleteMapping("/{product_id}")
    public void deleteWishlist(@Valid @MemberResolver Member member,
        @PathVariable(name = "product_id") long productId) {
        wishlistService.deleteWishlist(member, productId);
    }
}
