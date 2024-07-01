package gift.controller;

import gift.model.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product.Builder().build());
        return "addProduct";
    }

    @PostMapping
    public String addProduct(@RequestParam String name, @RequestParam int price, @RequestParam String imageUrl, Model model) {
        try {
            Product product = new Product.Builder()
                    .name(name)
                    .price(price)
                    .imageUrl(imageUrl)
                    .build();
            productService.addProduct(product);
            return "redirect:/products";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @RequestParam String name, @RequestParam int price, @RequestParam String imageUrl, Model model) {
        try {
            Product updatedProduct = new Product.Builder()
                    .id(id)
                    .name(name)
                    .price(price)
                    .imageUrl(imageUrl)
                    .build();
            productService.updateProduct(id, updatedProduct);
            return "redirect:/products";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        try {
            productService.deleteProduct(id);
            return "redirect:/products";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}