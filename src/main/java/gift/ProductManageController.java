package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/manage/products")
public class ProductManageController {

    private final ProductService productService;
    public ProductManageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String retrieveProduct(Model model) {
        model.addAttribute("products", productService.getProduct());
        return "manage-products";
    }

    @PostMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteTheProduct(productId);
        return "redirect:/api/manage/products";
    }

}
