package gift.controller;

import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepository;
import gift.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("product", new Product());
        return "product-list";
    }

    @PostMapping
    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        try {
            productRepository.save(product);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error adding product: " + e.getMessage());
        }
        return "redirect:/products";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product, RedirectAttributes redirectAttributes) {
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
            productRepository.deleteById(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting product: " + e.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/view/{id}")
    public String getProductDetails(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Product product = productRepository.findById(id);
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
            return productRepository.findById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Product not found: " + e.getMessage());
        }
    }
}