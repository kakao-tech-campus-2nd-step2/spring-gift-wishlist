package gift.controller;

import gift.dto.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.*;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private static final String REDIRECT_URL = "redirect:/api/products";
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(Model model) {
        List<Product> products = productService.getAllProducts();

        model.addAttribute("products", products);
        return "productList";
    }

    @PostMapping
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);

        return REDIRECT_URL;  // 새로운 상품 추가 후 상품 조회 화면으로 리다이렉트
    }

    @GetMapping("/new")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @GetMapping("/{id}/edit")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);

        if(product == null) {
            return REDIRECT_URL;
        }

        model.addAttribute("product", product);

        return "editProduct";
    }

    @PutMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {
        // 상품 정보 수정
        productService.updateProduct(id, product);

        return REDIRECT_URL;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        // 요청받은 id를 가진 상품을 삭제
        productService.deleteProduct(id);

        return REDIRECT_URL;
    }

}
