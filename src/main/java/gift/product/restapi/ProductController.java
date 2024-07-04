package gift.product.restapi;

import gift.core.product.Product;
import gift.core.product.ProductService;
import gift.core.product.exception.ProductNotFoundException;
import gift.product.restapi.dto.request.ProductCreateRequest;
import gift.product.restapi.dto.request.ProductUpdateRequest;
import gift.product.restapi.dto.response.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts() {
        return productService
                .findAll()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @GetMapping("/products/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return ProductResponse.from(productService.get(id));
    }

    @PostMapping("/products")
    public void addProduct(
            @Valid @RequestBody ProductCreateRequest request
    ) {
        Product product = productOf(request);
        productService.createProduct(product);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequest request
    ) {
        Product originalProduct = productService.get(id);
        if (originalProduct == null) {
            throw new ProductNotFoundException();
        }
        Product updatedProduct = originalProduct.applyUpdate(request.name(), request.price(), request.imageUrl());
        productService.updateProduct(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.remove(id);
    }

    private Product productOf(ProductCreateRequest request) {
        return new Product(
            0L,
                request.name(),
                request.price(),
                request.imageUrl()
        );
    }
}
