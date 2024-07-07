package gift.Controller;

import gift.Annotation.WishListResolver;
import gift.Model.User;
import gift.Model.WishListItem;
import gift.Service.UserService;
import gift.Service.WishListService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import java.util.List;

@Controller
public class WishListController {
    private final WishListService wishlistService;
    private final UserService userService;

    @Autowired
    public WishListController(WishListService wishlistService, UserService userService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
    }

    @GetMapping("/wishlist")
    public String getWishlist(Model model, HttpServletRequest request) {
        Cookie jwtCookie = WebUtils.getCookie(request, "token");
        String token = jwtCookie.getValue();
        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey().getBytes())
                .parseClaimsJws(token)
                .getBody();
        String userEmail = claims.getSubject();
        User user = userService.findByEmail(userEmail);
        List<WishListItem> wishlist = wishlistService.getWishlist(user.getId());
        model.addAttribute("wishlist", wishlist);
        return "wishlist";
    }

    public String getSigningKey() {
        return "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
    }

    @PostMapping("/wishlist/add")
    public String addWishlistItem(@WishListResolver WishListItem wishlistItem) {
        wishlistService.addWishlistItem(wishlistItem);
        return "redirect:/products";
    }

    @PostMapping("/wishlist/remove/{productId}")
    public String removeWishlistItem(@WishListResolver WishListItem wishListItem) {
        wishlistService.removeWishlistItem(wishListItem);
        return "redirect:/wishlist";
    }
}
