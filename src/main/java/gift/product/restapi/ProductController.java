package gift.product.restapi;

import gift.core.Product;
import gift.core.ProductNotFoundException;
import gift.product.persistence.ProductRepository;
import gift.product.restapi.dto.request.ProductCreateRequest;
import gift.product.restapi.dto.request.ProductUpdateRequest;
import gift.product.restapi.dto.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @GetMapping("/products/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return ProductResponse.from(productRepository.get(id));
    }

    @PostMapping("/products")
    public void addProduct(
            @RequestBody ProductCreateRequest productCreateRequest
    ) {
        Product product = productOf(productCreateRequest);
        productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequest productUpdateRequest
    ) {
        Product originalProduct = productRepository.get(id);
        if (originalProduct == null) {
            throw new ProductNotFoundException();
        }
        Product updatedProduct = applyUpdate(originalProduct, productUpdateRequest);
        productRepository.save(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.remove(id);
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
