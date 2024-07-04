package gift.controller;

import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepository;
import gift.model.Product;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductController(ProductRepository productRepository) {
    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("product", new Product());
        return "product-list";
    }

    @PostMapping
    public String addProduct(@ModelAttribute @Valid Product product, RedirectAttributes redirectAttributes) {
        try {
            productRepository.save(product);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error adding product: " + e.getMessage());
        }
        return "redirect:/products";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product, RedirectAttributes redirectAttributes) {
    public String updateProduct(@Valid @PathVariable Long id, @ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        try {
            product.setId(id);
            productRepository.update(product);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating product: " + e.getMessage());
        }
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
        } catch (ProductNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting product: " + e.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/view/{id}")
    public String getProductDetails(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Product product = productService.getProductById(id);
            model.addAttribute("product", product);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found: " + e.getMessage());
            return "redirect:/products";
        }
        return "product-detail";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable("id") Long id) {
        try {
            return productService.getProductById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Product not found: " + e.getMessage());
        }
    }
}