package gift.controller;

import gift.service.ProductService;
import gift.model.Product;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductViewController {
    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String products(Model model) {
        List<Product> products = productService.getAllProducts();

        model.addAttribute("products", products);
        return "product_list";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product(null, "", null, ""));
        return "product_add_form";
    }

    @GetMapping("edit/{id}")
    public String editProductPage(Model model, @PathVariable Long id) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product_edit_form";
    }

}