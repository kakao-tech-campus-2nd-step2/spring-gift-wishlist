package gift.Controller;

import gift.Annotation.LoginMemberResolver;
import gift.Model.User;
import gift.Model.WishListItem;
import gift.Service.UserService;
import gift.Service.WishListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WishListController {
    @Value("${jwt.secretKey}")
    private String secretKey;
    private final WishListService wishlistService;
    private final UserService userService;

    @Autowired
    public WishListController(WishListService wishlistService, UserService userService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
    }

    @GetMapping("/wishlist")
    public String getWishlist(@LoginMemberResolver User user, Model model, HttpServletRequest request) {
        /*
        Cookie jwtCookie = WebUtils.getCookie(request, "token");
        String token = jwtCookie.getValue();
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
        String userEmail = claims.getSubject();
        User user = userService.findByEmail(userEmail);

         */
        List<WishListItem> wishlist = wishlistService.getWishlist(user.getId());
        model.addAttribute("wishlist", wishlist);
        return "wishlist";
    }

    @PostMapping("/wishlist/add")
    public String addWishlistItem(@LoginMemberResolver User user, @RequestBody WishListItem wishListItem) {
        if(user == null) {
            return "redirect:/login";
        }
        wishListItem.setUserId(user.getId());
        wishlistService.addWishlistItem(wishListItem);
        return "redirect:/products";
    }

    @PostMapping("/wishlist/remove/{productId}")
    public String removeWishlistItem(@LoginMemberResolver User user, @RequestBody WishListItem wishListItem) {
        if(user == null) {
            return "redirect:/login";
        }
        wishListItem.setUserId(user.getId());
        wishlistService.removeWishlistItem(wishListItem);
        return "redirect:/wishlist";
    }
}
