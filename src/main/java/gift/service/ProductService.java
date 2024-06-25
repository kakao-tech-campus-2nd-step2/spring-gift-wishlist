package gift.service;

import gift.converter.ProductConverter;
import gift.domain.Product;
import gift.repository.ProductRepository;
import gift.web.dto.request.CreateProductRequest;
import gift.web.dto.response.CreateProductResponse;
import gift.web.dto.response.ReadAllProductsResponse;
import java.util.UUID;
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

    public ReadAllProductsResponse readAllProducts() {
        return new ReadAllProductsResponse(productRepository.findAll());
    }

    /**
     * 상품을 삭제합니다.
     * @param id 상품 아이디
     * @return ture : 삭제 성공, false : 삭제 실패
     */
    public boolean deleteProduct(String id) {
        return productRepository.deleteById(UUID.fromString(id));
    }
}
