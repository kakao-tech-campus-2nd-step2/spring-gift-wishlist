package gift.product.controller;

import gift.product.model.Product;
import gift.product.service.ProductService;
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

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public String registerProduct(@ModelAttribute("name") String name,
                             @ModelAttribute("price") int price,
                             @ModelAttribute("imageUrl") String imageUrl,
                             Model model) {
        productService.registerProduct(new Product(idCounter.incrementAndGet(), name, price, imageUrl));
        model.addAttribute("productList", productService.getAllProducts());
        System.out.println("[ProductController] registerProduct()");
        return "product";
    }

    @PutMapping()
    public String updateProduct(@ModelAttribute("id") Long id,
                                @ModelAttribute("name") String name,
                                @ModelAttribute("price") int price,
                                @ModelAttribute("imageUrl") String imageUrl,
                                Model model) {
        productService.updateProduct(new Product(id, name, price, imageUrl));
        model.addAttribute("productList", productService.getAllProducts());
        return "product";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
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
