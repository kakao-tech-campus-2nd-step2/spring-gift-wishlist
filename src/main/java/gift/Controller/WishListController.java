package gift.Controller;

import gift.Model.User;
import gift.Model.WishListItem;
import gift.Service.UserService;
import gift.Service.WishListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishListController {
    private final WishListService wishlistService;
    private final UserService userService;

    @Autowired
    public WishListController(WishListService wishlistService, UserService userService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
    }

    @GetMapping
    public String getWishlist(Model model, HttpServletRequest request) {
        String userEmail = (String) request.getAttribute("userEmail");
        User user = userService.findByEmail(userEmail);
        List<WishListItem> wishlist = wishlistService.getWishlist(user.getId());
        model.addAttribute("wishlist", wishlist);
        return "wishlist"; // 이 부분이 변경되었습니다.
    }

    @PostMapping("/add")
    public String addWishlistItem(HttpServletRequest request, @RequestParam int productId) {
        String userEmail = (String) request.getAttribute("userEmail");
        User user = userService.findByEmail(userEmail);
        wishlistService.addWishlistItem(user.getId(), productId);
        return "redirect:/wishlist";
    }

    @PostMapping("/remove/{productId}")
    public String removeWishlistItem(HttpServletRequest request, @PathVariable int productId) {
        String userEmail = (String) request.getAttribute("userEmail");
        User user = userService.findByEmail(userEmail);
        wishlistService.removeWishlistItem(user.getId(), productId);
        return "redirect:/wishlist";
    }
}
