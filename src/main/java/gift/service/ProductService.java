package gift.service;

import gift.converter.ProductConverter;
import gift.domain.Product;
import gift.domain.dto.ProductUpdateParam;
import gift.repository.ProductRepository;
import gift.web.dto.request.CreateProductRequest;
import gift.web.dto.request.UpdateProductRequest;
import gift.web.dto.response.CreateProductResponse;
import gift.web.dto.response.ReadAllProductsResponse;
import gift.web.dto.response.UpdateProductResponse;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
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

    public UpdateProductResponse updateProduct(String id, UpdateProductRequest request) {
        if(!productRepository.existsById(UUID.fromString(id))) {
            throw new NoSuchElementException(id + "에 해당하는 상품이 없습니다.");
        }

        String name = request.getName();
        Integer price = request.getPrice();
        URL imageUrl = request.getImageUrl();
        ProductUpdateParam productUpdateParam = new ProductUpdateParam(name, price, imageUrl);

        productRepository.update(UUID.fromString(id), productUpdateParam);

        Product updatedProduct = productRepository.findById(UUID.fromString(id))
            .orElseThrow(NoSuchElementException::new);
        return UpdateProductResponse.from(updatedProduct);
    }

    /**
     * 상품을 삭제합니다.
     * @param id 상품 아이디
     * @return true : 삭제 성공, false : 삭제 실패
     */
    public boolean deleteProduct(String id) {
        return productRepository.deleteById(UUID.fromString(id));
    }
}
