package gift.Controller;


import gift.Model.Product;

import gift.Service.ProductService;
import gift.Service.UserService;
import gift.Service.WishlistService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class WIshListController {
    private final ProductService productService;
    private final WishlistService wishlistService;
    private final UserService userService;

    public WIshListController(ProductService productService, WishlistService wishlistService,UserService userService){
        this.productService = productService;
        this.wishlistService = wishlistService;
        this.userService = userService;
    }

    @GetMapping("/api/wish")
    public String getWish(HttpServletRequest request,Model model) {
        String email = (String) request.getAttribute("email");
//        if(!userService.checkUserByMemberEmail(email)){
//            throw new IllegalArgumentException();
//        }
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("wishlists", wishlistService.getAllWishlist());
        return "wish";
    }

    @PostMapping("/api/wish")
    public String getWishLists(Model model) {
        return "redirect:/api/wish";
    }

    @PostMapping("/api/wish/add/{id}")
    public String editWishForm(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
//        if(!userService.checkUserByMemberEmail(email)){
//            throw new IllegalArgumentException();
//        }
        Product product = productService.getProductById(id);
        wishlistService.addWishlist(product);
        return "redirect:/api/wish";
    }

    @PostMapping("/api/wish/delete/{id}")
    public String deleteWish(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
//        if(!userService.checkUserByMemberEmail(email)) {
//            throw new IllegalArgumentException();
//        }
        wishlistService.deleteWishlist(id);
        return "redirect:/api/wish";
    }
}
