package gift.controller;

import gift.domain.Product;
import gift.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "productForm";
    }

    @PostMapping
    public String createProduct(@Valid  @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "productForm";
        }
        // "카카오" 문구 포함 여부 확인하는 로직 추가
        if (product.getName().contains("카카오")) {
            model.addAttribute("errorMessage", "담당 MD와 협의된 경우에만 '카카오'를 포함할 수 있습니다.");
            return "productForm";
        }
        productRepository.save(product);
        return "redirect:/api/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + id));

        model.addAttribute("product", product);
        return "productForm";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute Product updatedProduct, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "productForm";
        }
        // "카카오" 문구 포함 여부 확인
        if (updatedProduct.getName().contains("카카오")) {
            model.addAttribute("errorMessage", "담당 MD와 협의된 경우에만 '카카오'를 포함할 수 있습니다.");
            return "productForm";
        }
        updatedProduct.setId(id);
        productRepository.update(updatedProduct);
        return "redirect:/api/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/api/products";
    }
}
