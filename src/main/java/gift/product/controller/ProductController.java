package gift.product.controller;

import gift.global.response.ResultCode;
import gift.global.response.ResultResponseDto;
import gift.global.response.SimpleResultResponseDto;
import gift.product.dto.ProductRequest;
import gift.product.domain.Product;
import gift.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<ResultResponseDto<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return createResponse(ResultCode.GET_ALL_PRODUCTS_SUCCESS, products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultResponseDto<Product>> getProductById(@PathVariable(name = "id") Long id) {
        Product product = productService.getProductById(id);
        return createResponse(ResultCode.GET_PRODUCT_BY_ID_SUCCESS, product);
    }

    @PostMapping("")
    public ResponseEntity<SimpleResultResponseDto> createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest.toServiceDto());
        return createSimpleResponse(ResultCode.CREATE_PRODUCT_SUCCESS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleResultResponseDto> updateProduct(@PathVariable(name = "id") Long id, @RequestBody ProductRequest productRequest) {
        productService.updateProduct(productRequest.toServiceDto(id));
        return createSimpleResponse(ResultCode.UPDATE_PRODUCT_SUCCESS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleResultResponseDto> deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
        return createSimpleResponse(ResultCode.DELETE_PRODUCT_SUCCESS);
    }

    private <T> ResponseEntity<ResultResponseDto<T>> createResponse(ResultCode resultCode, T data) {
        ResultResponseDto<T> resultResponseDto = new ResultResponseDto<>(resultCode, data);
        return ResponseEntity.status(resultCode.getStatus())
                .body(resultResponseDto);
    }

    private ResponseEntity<SimpleResultResponseDto> createSimpleResponse(ResultCode resultCode) {
        var resultResponseDto = new SimpleResultResponseDto(resultCode);
        return ResponseEntity.status(resultCode.getStatus())
                .body(resultResponseDto);
    }
}