package gift.service;

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
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public CreateProductResponse createProduct(CreateProductRequest request) {
        Product product = request.toEntity();
        return new CreateProductResponse(productRepository.save(product));
    }

    public Product searchProduct(Long id) {
        return productRepository.findById(id)
            .orElseThrow(NoSuchElementException::new);
    }

    public ReadAllProductsResponse readAllProducts() {
        return new ReadAllProductsResponse(productRepository.findAll());
    }

    public UpdateProductResponse updateProduct(Long id, UpdateProductRequest request) {
        if(!productRepository.existsById(id)) {
            throw new NoSuchElementException(id + "에 해당하는 상품이 없습니다.");
        }

        String name = request.getName();
        Integer price = request.getPrice();
        URL imageUrl = request.getImageUrl();
        ProductUpdateParam productUpdateParam = new ProductUpdateParam(name, price, imageUrl);

        productRepository.update(id, productUpdateParam);

        Product updatedProduct = productRepository.findById(id)
            .orElseThrow(NoSuchElementException::new);
        return UpdateProductResponse.from(updatedProduct);
    }

    /**
     * 상품을 삭제합니다.
     * @param id 상품 아이디
     * @return true : 삭제 성공, false : 삭제 실패
     */
    public boolean deleteProduct(Long id) {
        return productRepository.deleteById(id);
    }
}
