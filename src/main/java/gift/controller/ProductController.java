package gift.controller;

import gift.domain.Product;
import gift.service.ProductService;
<<<<<<< HEAD
import jakarta.validation.Valid;
=======
>>>>>>> jjt4515
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
//@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public String getProducts(Model model){
        model.addAttribute("products", productService.findProducts());
        return "product-list";
    }

    @GetMapping("/api/products/{id}")
    public String getProduct(@PathVariable Long id, Model model){
        model.addAttribute("products", productService.findOne(id));
        return "product-list";
    }

    @GetMapping("/api/products/new")
    public String newProductForm(Model model){
        model.addAttribute("product", new ProductRequest());
        return "product-add-form";
    }

    @PostMapping("/api/products")
<<<<<<< HEAD
    public String addProduct(@Valid @ModelAttribute ProductRequest productRequest) {
=======
    public String addProduct(@ModelAttribute ProductRequest productRequest) {
>>>>>>> jjt4515
        productService.register(productRequest);
        return "redirect:/api/products";
    }

    @GetMapping("/api/products/edit/{id}")
    public String editProductForm(@PathVariable long id, Model model){
<<<<<<< HEAD
        Product product = productService.findOne(id);
        model.addAttribute("product", product);
        return "product-edit-form";
    }
    @PostMapping("/api/products/edit/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute ProductRequest productRequest) {
=======
        Optional<Product> product = productService.findOne(id);
        if (product.isPresent()){
            model.addAttribute("product", product.get());
            return "product-edit-form";
        };
        return "redirect:/api/products";
    }
    @PostMapping("/api/products/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ProductRequest productRequest) {
>>>>>>> jjt4515
        productService.update(id, productRequest);
        return "redirect:/api/products";
    }

    @GetMapping("/api/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/api/products";

    }
}
