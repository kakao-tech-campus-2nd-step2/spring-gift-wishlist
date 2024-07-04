package gift.product.controller;

import gift.product.dto.AdminProductDto;
import gift.product.model.Product;
import gift.product.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    public static final String REDIRECT_ADMIN_PRODUCTS = "redirect:/admin/products";
    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String products(Model model) {
        List<Product> products = productService.getProductAll();
        model.addAttribute("products", products);
        return "admin/products";
    }

    @GetMapping("/insert")
    public String insertForm() {
        return "admin/insertForm";
    }

    @PostMapping("/insert")
    public String insertProduct(@Valid AdminProductDto adminProductDto) {
        productService.insertProduct(adminProductDto);
        return REDIRECT_ADMIN_PRODUCTS;
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable(name = "id") Long productId, Model model) {
        Product product = productService.getProduct(productId);
        model.addAttribute("product", product);
        return "admin/updateForm";
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable(name = "id") Long productId,
        @Valid AdminProductDto adminProductDto) {
        productService.updateProduct(productId, adminProductDto);
        return REDIRECT_ADMIN_PRODUCTS;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long productId) {
        productService.deleteProduct(productId);
        return REDIRECT_ADMIN_PRODUCTS;
    }
}
