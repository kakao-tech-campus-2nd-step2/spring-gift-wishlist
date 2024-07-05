package gift.view;

import gift.controller.ProductController;
import gift.controller.WishlistController;
import gift.model.Product;
import gift.model.WishlistItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistViewController {
    private WishlistController wishlistController;

    public WishlistViewController(WishlistController wishlistController) {
        this.wishlistController = wishlistController;
    }

    @GetMapping("{id}")
    public String showWishlist(@PathVariable("id") Long userId, Model model) {
        List<WishlistItem> wishlists = wishlistController.makeWishlist(userId).getBody();
        model.addAttribute("wishlists", wishlists);
        return "wishlist";
    }
}
