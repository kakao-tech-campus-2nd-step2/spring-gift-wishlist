package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;
import gift.validator.ProductNameValidator;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductNameValidator productNameValidator;

    public ProductService(ProductRepository productRepository, ProductNameValidator productNameValidator) {
        this.productRepository = productRepository;
        this.productNameValidator = productNameValidator;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    private void validateProduct(Product product) {
        productNameValidator.validate(product, new org.springframework.validation.DirectFieldBindingResult(product, "product"));
    }

}
