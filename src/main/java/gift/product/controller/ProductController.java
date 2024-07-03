package gift.product.controller;

import gift.product.model.Product;
import gift.product.service.ProductService;
import gift.product.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final AtomicLong idCounter = new AtomicLong();
    private final ProductValidation productValidation;

    @Autowired
    public ProductController(ProductService productService, ProductValidation productValidation) {
        this.productService = productService;
        this.productValidation = productValidation;
    }

    @PostMapping()
    public String registerProduct(@ModelAttribute("product") Product product, Model model) {
        productService.registerProduct(new Product(idCounter.incrementAndGet(), product.getName(), product.getPrice(), product.getImageUrl()));
        model.addAttribute("productList", productService.getAllProducts());
        return "product";
    }

    @PutMapping()
    public String updateProduct(@ModelAttribute("product") Product product, Model model) {
        productService.updateProduct(product);
        model.addAttribute("productList", productService.getAllProducts());
        return "product";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        if(productValidation.existsById(id))
            productService.deleteProduct(id);
        model.addAttribute("productList", productService.getAllProducts());
        return "product";
    }

    @GetMapping("/admin")
    public String listupProduct(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        return "product";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("searchResults", productService.searchProducts(keyword));
        model.addAttribute("keyword", keyword);
        return "product";
    }
}
