package gift.view;

import gift.controller.ProductController;
import gift.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/withlist")
public class WishlistViewController {
    private final ProductController productController;

    public WishlistViewController(ProductController productController) {
        this.productController = productController;
    }

    @GetMapping("")
    public String getAllProducts(Model model) {
        List<Product> products = productController.getAllProducts().getBody();
        model.addAttribute("products", products);
        return "withlist";
    }
}
