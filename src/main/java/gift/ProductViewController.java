package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductViewController {
    private final ProductController productController;

    public ProductViewController(ProductController productController) {
        this.productController = productController;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productController.getAllProducts());
        return "products";
    }

    @GetMapping("/new")
    public String showAddProductForm() {
        return "product_form";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productController.getAllProducts().stream()
                .filter(p -> p.id.equals(id))
                .findFirst()
                .orElse(null);
        if (product != null) {
            model.addAttribute("product", product);
            return "product_edit_form";
        }
        return "redirect:/products";
    }
}
