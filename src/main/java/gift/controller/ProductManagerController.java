package gift.controller;


import gift.dto.RequestProductDto;
import gift.dto.ResponseProductDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products/manager")
public class ProductManagerController {
    private final ProductService productService;

    public ProductManagerController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String productManager(Model model) {
        List<ResponseProductDto> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        return "products-manager";
    }

    @PostMapping
    public String addProduct(@ModelAttribute RequestProductDto requestProductDto) {
        productService.createProduct(requestProductDto);
        return "redirect:/products/manager";
    }
}
