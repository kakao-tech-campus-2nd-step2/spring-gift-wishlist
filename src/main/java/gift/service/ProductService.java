package gift.service;

import gift.dto.ProductResponse;
import gift.model.Product;
import gift.dto.ProductRequest;
import gift.repository.ProductOptionRepository;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository optionRepository;

    public ProductService(ProductRepository productRepository, ProductOptionRepository optionRepository) {
        this.productRepository = productRepository;
        this.optionRepository = optionRepository;
    }

    public ProductResponse addProduct(ProductRequest productRequest) {
        var product = createProductWithProductRequest(productRequest);
        var savedProduct = productRepository.save(product);
        return ProductResponse.from(savedProduct);
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        var product = findProductWithId(id);
        var updatedProduct = updateProductWithId(product, productRequest);
        return ProductResponse.from(updatedProduct);
    }

    public ProductResponse getProduct(Long id) {
        var product = findProductWithId(id);
        return ProductResponse.from(product);
    }

    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    public void deleteProduct(Long id) {
        optionRepository.deleteByProductId(id);
        productRepository.deleteById(id);
    }

    private Product findProductWithId(Long id) {
        return productRepository.findById(id);
    }

    private Product createProductWithProductRequest(ProductRequest productRequest) {
        return new Product(productRequest.name(), productRequest.price(), productRequest.imageUrl());
    }

    private Product updateProductWithId(Product product, ProductRequest productRequest) {
        product.updateProductInfo(productRequest.name(), productRequest.price(), productRequest.imageUrl());
        productRepository.update(product);
        return product;
    }
}
