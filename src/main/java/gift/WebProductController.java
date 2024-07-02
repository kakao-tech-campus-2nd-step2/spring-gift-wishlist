package gift;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebProductController {
    private final ProductService productService;

    public WebProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String viewHomepage(Model model) {
        List<Product> products = productService.findAll();
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
    public String saveProducts(@ModelAttribute("product") Product product, Model model) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/showUpdateProducts/{id}")
    public String showUpdateProducts(@PathVariable(value = "id") long id, Model model) {
        Optional<Product> product = productService.findById(id);
        if (product.isEmpty()) {
            return "redirect:/products";
        }
        model.addAttribute("product", product.get());
        return "updateProduct";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable long id) {
        if (productService.findById(id).isEmpty()) {
            return "redirect:/products";
        }
        productService.deleteById(id);
        return "redirect:/products";
    }

}
