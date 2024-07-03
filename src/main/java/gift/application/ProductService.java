package gift.application;

import gift.dao.ProductRepository;
import gift.domain.Product;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 상품은 존재하지 않습니다")
        );
        return ProductMapper.toResponseDto(product);
    }

    public ProductResponse createProduct(ProductRequest request) {
        return ProductMapper.toResponseDto(
                productRepository.save(ProductMapper.toEntity(request))
        );
    }

    public Long deleteProductById(Long id) {
        productRepository.deleteById(id);
        return id;
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    public Long updateProduct(Long id, ProductRequest request) throws NoSuchElementException {
        getProductById(id); // 상품 존재 여부 확인
        productRepository.update(id, ProductMapper.toEntity(request));
        return id;
    }

}