package gift.service;

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

    public Product addProduct(ProductRequest productRequest) {
        Long id = repository.save(Product.from(productRequest));

        Product product = new Product(id, productRequest.name(), productRequest.price(), productRequest.imageUrl());
        return product;
    }

    public Product updateProduct(Long id, ProductRequest productRequest) {
        var product = repository.findById(id);
        product.updateFrom(productRequest);
        repository.update(product);
        return product;
    }

    public Product getProduct(Long id) {
        return repository.findById(id);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public void deleteProduct(Long id) {
        optionRepository.deleteByProductId(id);
        repository.deleteById(id);
    }
}
