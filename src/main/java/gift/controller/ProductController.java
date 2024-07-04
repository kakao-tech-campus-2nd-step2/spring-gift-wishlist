package gift.controller;

import gift.Product;
import gift.ProductDto;
import gift.service.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 모든 제품 조회
    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    // 특정 제품 조회
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // 제품 추가
    @PostMapping("/add")
    public ProductDto addProduct(@ModelAttribute ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    // 제품 수정
    @PostMapping("/update/{id}")
    public ProductDto updateProduct(@ModelAttribute ProductDto productDto) {
        return productService.updateProduct(productDto);

    }

    // 제품 삭제
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "{status: success}";
    }
}
