package gift.application;

import gift.dao.ProductRepository;
import gift.domain.Product;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponse> responseList = new ArrayList<>();
        for (Product product : productList) {
            responseList.add(product.toResponseDto());
        }
        return responseList;
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 상품은 존재하지 않습니다")
        );
        return product.toResponseDto();
    }

    public ProductResponse createProduct(ProductRequest request) {
        return productRepository.save(request.toEntity()).toResponseDto();
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
        productRepository.update(id, request.toEntity());
        return id;
    }

}