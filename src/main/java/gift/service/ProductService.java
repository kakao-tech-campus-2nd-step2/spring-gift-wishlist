package gift.service;

import gift.converter.ProductConverter;
import gift.domain.Product;
import gift.repository.ProductRepository;
import gift.web.dto.request.CreateProductRequest;
import gift.web.dto.response.CreateProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductService(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    public CreateProductResponse createProduct(CreateProductRequest request) {
        Product product = productConverter.convertToEntity(request);
        return new CreateProductResponse(productRepository.save(product));
    }

}
