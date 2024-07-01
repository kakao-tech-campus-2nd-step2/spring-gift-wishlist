package gift.controller;

import gift.dto.ProductRequest;
import gift.model.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService service;

    public AdminController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        List<Product> products = service.getProducts();
        model.addAttribute("data", "관리자");
        model.addAttribute("products", products);
        return "views/products";
    }

    @GetMapping("/products/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Product product = service.getProduct(id);
        model.addAttribute("product", product);
        return "views/product";
    }

    @GetMapping("/products/add")
    public String getAddForm() {
        return "views/addProduct";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute ProductRequest productRequest, RedirectAttributes redirectAttributes) {
        Product product = service.addProduct(productRequest);

        redirectAttributes.addAttribute("productId", product.getId());

        return "redirect:/admin/products/{productId}";
    }

    @GetMapping("/products/edit/{id}")
    public String getEditForm(@PathVariable Long id, Model model) {
        Product product = service.getProduct(id);
        model.addAttribute("product", product);
        return "views/editProduct";
    }
}
