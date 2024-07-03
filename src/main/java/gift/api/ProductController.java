package gift.api;

import gift.application.ProductService;
import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 전체 조회
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    // 상품 상세 조회
    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable("id") Long id) {
        return productService.getProductByIdOrThrow(id);
    }

    // 상품 추가
    @PostMapping
    public ProductResponse addProduct(@RequestBody @Valid ProductRequest request) {
        return productService.createProduct(request);
    }

    // 상품 하나 삭제
    @DeleteMapping("/{id}")
    public Long deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProductById(id);
    }

    // 상품 전체 삭제
    @DeleteMapping
    public void deleteAllProducts() {
        productService.deleteAllProducts();
    }

    // 상품 수정
    @PatchMapping("/{id}")
    public Long updateProduct(@PathVariable("id") Long id,
                              @RequestBody @Valid ProductRequest request) {
        return productService.updateProduct(id, request);
    }

}
