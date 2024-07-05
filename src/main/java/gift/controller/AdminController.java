package gift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import gift.model.Product;
import gift.service.ProductService;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String adminPage(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        return "admin";
    }

    @GetMapping("/create")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product(null, "", 0, ""));
        return "product-form";
    }

    @PostMapping("/create")
    public String addProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProduct(id);
        if (product == null) {
            return "redirect:/admin";
        }
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, @ModelAttribute Product updatedProduct) {
        int rowsAffected = productService.updateProduct(updatedProduct);
        if (rowsAffected == 0) {
            return "redirect:/admin";
        }
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        int rowsAffected = productService.deleteProduct(id);
        if (rowsAffected == 0) {
            return "redirect:/admin";
        }
        return "redirect:/admin";
    }
}
