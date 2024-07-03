package gift;

import jakarta.validation.ValidationException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String allProducts(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "Products";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "Add_product";
    }

    @PostMapping
    public String addProduct(@ModelAttribute Product product, Model model) {
        try {
            productService.addProduct(product);
        } catch (ValidationException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("product", product);
            return "Add_product"; // 예외 발생 시 입력 페이지로 리턴
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "Edit_product";
    }

    @PutMapping("/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product product, Model model) {
        try {
            productService.updateProduct(id, product);
        } catch (ValidationException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("product", product);
            return "Edit_product"; // 예외 발생 시 입력 페이지로 리턴
        }
        return "redirect:/admin/products";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}