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
            @Valid @RequestBody ProductCreateRequest productCreateRequest
    ) {
        Product product = productOf(productCreateRequest);
        productService.createProduct(product);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequest productUpdateRequest
    ) {
        Product originalProduct = productService.get(id);
        if (originalProduct == null) {
            throw new ProductNotFoundException();
        }
        Product updatedProduct = applyUpdate(originalProduct, productUpdateRequest);
        productService.updateProduct(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.remove(id);
    }

    private Product productOf(ProductCreateRequest productCreateRequest) {
        return new Product(
            0L,
                productCreateRequest.name(),
                productCreateRequest.price(),
                productCreateRequest.imageUrl()
        );
    }

    private Product applyUpdate(Product originalProduct, ProductUpdateRequest productUpdateRequest) {
        String name = originalProduct.name();
        if (productUpdateRequest.name() != null) {
            name = productUpdateRequest.name();
        }

        Integer price = originalProduct.price();
        if (productUpdateRequest.price() != null) {
            price = productUpdateRequest.price();
        }

        String imageUrl = originalProduct.imageUrl();
        if (productUpdateRequest.imageUrl() != null) {
            imageUrl = productUpdateRequest.imageUrl();
        }
        return new Product(originalProduct.id(), name, price, imageUrl);
    }
}
