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

    @GetMapping("/register")
    public String showProductForm(Model model) {
        System.out.println("[ProductController] showProductForm()");
        model.addAttribute("product", new Product(idCounter.incrementAndGet(), "", 0, ""));
        return "product-form";
    }

    @PostMapping()
    public String registerProduct(@ModelAttribute("id") Long id,
                                    @ModelAttribute("name") String name,
                                    @ModelAttribute("price") int price,
                                    @ModelAttribute("imageUrl") String imageUrl,
                                    Model model) {
        System.out.println("[ProductController] registerProduct()");
        productService.registerProduct(new Product(id, name, price, imageUrl));
        return "redirect:/product/admin";
    }

    @PutMapping()
    public String updateProduct(@ModelAttribute("id") Long id,
                                @ModelAttribute("name") String name,
                                @ModelAttribute("price") int price,
                                @ModelAttribute("imageUrl") String imageUrl,
                                Model model) {
        System.out.println("[ProductController] updateProduct()");
        productService.updateProduct(new Product(id, name, price, imageUrl));
        model.addAttribute("productList", productService.getAllProducts());
        return "product";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        System.out.println("[ProductController] deleteProduct()");
        if(productValidation.existsById(id))
            productService.deleteProduct(id);
        model.addAttribute("productList", productService.getAllProducts());
        return "product";
    }

    @GetMapping("/admin")
    public String listupProduct(Model model) {
        System.out.println("[ProductController] listupProduct()");
        model.addAttribute("productList", productService.getAllProducts());
        return "product";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam("keyword") String keyword, Model model) {
        System.out.println("[ProductController] searchProduct()");
        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("searchResults", productService.searchProducts(keyword));
        model.addAttribute("keyword", keyword);
        return "product";
    }
}
