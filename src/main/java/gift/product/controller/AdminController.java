package gift.product.controller;

import gift.product.dto.ProductDto;
import gift.product.model.Product;
import gift.product.repository.ProductRepository;
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
    private final ProductRepository productRepository;

    public AdminController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String products(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "admin/products";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long productId) {
        productRepository.delete(productId);
        return REDIRECT_ADMIN_PRODUCTS;
    }

    @GetMapping("/insert")
    public String insertForm() {
        return "admin/insertForm";
    }

    @PostMapping("/insert")
    public String insertProduct(ProductDto productDto) {
        productRepository.save(new Product(productDto.name(), productDto.price(), productDto.imageUrl()));
        return REDIRECT_ADMIN_PRODUCTS;
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable(name = "id") Long productId, Model model) {
        Product product = productRepository.findById(productId);
        model.addAttribute("product", product);
        return "admin/updateForm";
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable(name = "id") Long productId, ProductDto productDto) {
        productRepository.update(new Product(productId, productDto.name(), productDto.price(), productDto.imageUrl()));
        return REDIRECT_ADMIN_PRODUCTS;
    }
}
