package gift.controller;

import gift.repository.ProductRepository;
import gift.model.Product;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ThymeleafProductController {

    @Autowired
    private ProductRepository productDatabase;

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productDatabase.findAll();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/new")
    public String createProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "create-product";
    }

    @PostMapping
    public String addProduct(@ModelAttribute("product") Product product) {
        productDatabase.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Optional<Product> productOpt = productDatabase.findById(id);
        if (productOpt.isEmpty()) {
            return "redirect:/products";
        }
        model.addAttribute("product", productOpt.get());
        return "edit-product";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {
        Optional<Product> existingProductOpt = productDatabase.findById(id);
        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImageUrl(product.getImageUrl());
            productDatabase.save(existingProduct);
        }
        return "redirect:/products";
    }


    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        Optional<Product> productOpt = productDatabase.findById(id);
        if (productOpt.isPresent()) {
            productDatabase.deleteById(id);
        }
        return "redirect:/products";
    }

}
