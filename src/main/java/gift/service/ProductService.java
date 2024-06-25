package gift.service;

import gift.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
