package gift.Controller;


import gift.Model.Product;

import gift.Service.MemberService;
import gift.Service.ProductService;
import gift.Service.WishlistService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class WishListController {
    private final ProductService productService;
    private final WishlistService wishlistService;
    private final MemberService memberService;

    public WishListController(ProductService productService, WishlistService wishlistService, MemberService memberService){
        this.productService = productService;
        this.wishlistService = wishlistService;
        this.memberService = memberService;
    }

    @GetMapping("/api/wish")
    public String getWish(HttpServletRequest request,Model model) {
        String email = (String) request.getAttribute("email");
        if(email != null && !memberService.checkUserByMemberEmail(email)){
            // 분리 후 다시 작성
        }
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
        if(email != null && !memberService.checkUserByMemberEmail(email)){
            // 분리 후 다시 작성
        }
        Product product = productService.getProductById(id);
        wishlistService.addWishlist(product);
        return "redirect:/api/wish";
    }

    @PostMapping("/api/wish/delete/{id}")
    public String deleteWish(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        if(email != null && !memberService.checkUserByMemberEmail(email)){
            // 분리 후 다시 작성
        }
        wishlistService.deleteWishlist(id);
        return "redirect:/api/wish";
    }
}
