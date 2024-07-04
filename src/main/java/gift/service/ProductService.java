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

    private final ProductRepository repository;
    private final ProductOptionRepository optionRepository;

    public ProductService(ProductRepository repository, ProductOptionRepository optionRepository) {
        this.repository = repository;
        this.optionRepository = optionRepository;
    }

    public ProductResponse addProduct(ProductRequest productRequest) {
        var product = repository.save(Product.from(productRequest));
        return ProductResponse.from(product);
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        var product = updateProductWithId(id, productRequest);
        return ProductResponse.from(product);
    }

    public ProductResponse getProduct(Long id) {
        var product = repository.findById(id);
        return ProductResponse.from(product);
    }

    public List<ProductResponse> getProducts() {
        return repository.findAll()
                .stream()
                .map((ProductResponse::from))
                .toList();
    }

    public void deleteProduct(Long id) {
        optionRepository.deleteByProductId(id);
        repository.deleteById(id);
    }

    private Product updateProductWithId(Long id, ProductRequest productRequest) {
        var product = repository.findById(id);
        product.updateFrom(productRequest);
        repository.update(product);
        return product;
    }
}
