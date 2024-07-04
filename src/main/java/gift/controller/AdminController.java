package gift.controller;

import gift.dto.ProductRequest;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService service;

    public AdminController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        var products = service.getProducts();
        model.addAttribute("data", "관리자");
        model.addAttribute("products", products);
        return "views/products";
    }

    @GetMapping("/products/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        var product = service.getProduct(id);
        model.addAttribute("product", product);
        return "views/product";
    }

    @GetMapping("/products/add")
    public String getAddForm() {
        return "views/addProduct";
    }

    @PostMapping("/products/add")
    public String addProduct(@Valid @ModelAttribute ProductRequest productRequest, RedirectAttributes redirectAttributes) {
        var product = service.addProduct(productRequest);
        redirectAttributes.addAttribute("productId", product.id());
        return "redirect:/admin/products/{productId}";
    }

    @GetMapping("/products/edit/{id}")
    public String getEditForm(@PathVariable Long id, Model model) {
        var product = service.getProduct(id);
        model.addAttribute("product", product);
        return "views/editProduct";
    }
}
