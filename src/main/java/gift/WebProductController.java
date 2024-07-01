package gift;

import java.util.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebProductController {
    private final ProductController productController;

    public WebProductController(ProductController productController) {
        this.productController = productController;
    }

    @GetMapping("/products")
    public String ViewHomepage(Model model) {
        Collection<Product> products = productController.getAllProducts().getBody();
        model.addAttribute("productsList", products);
        return "index";
    }

    @GetMapping("/showNewProducts")
    public String showNewProducts(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "newProduct";
    }

    @PostMapping("/saveProducts")
    public String saveProducts(@ModelAttribute("product") Product product) {
        if (product.getId() != 0) {
            productController.updateProduct(product.getId(), product);
            return "redirect:/products";
        }
        productController.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/showUpdateProducts/{id}")
    public String showUpdateProducts(@PathVariable(value = "id") long id, Model model) {
        Product product = productController.getProduct(id).getBody();
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "updateProduct";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable long id) {
        productController.deleteProduct(id);
        return "redirect:/products";
    }

}
