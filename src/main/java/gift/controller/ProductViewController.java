package gift.controller;

import gift.domain.model.ProductDto;
import gift.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String listProducts(Model model) {
        List<ProductDto> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "index";
    }
}