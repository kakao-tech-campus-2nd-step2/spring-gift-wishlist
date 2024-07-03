package gift.controller;

import gift.service.ProductService;
import gift.domain.model.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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