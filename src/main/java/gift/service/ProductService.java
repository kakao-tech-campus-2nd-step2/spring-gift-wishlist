package gift.service;

import gift.exception.ProductAlreadyExistsException;
import gift.exception.ProductNotFoundException;
import gift.model.Product;
import gift.model.ProductDto;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id) {
        //존재하지 않는 상품 참조 시도시 예외 발생
        Optional<Product> product = productRepository.findById(id);
        product.orElseThrow(ProductNotFoundException::new);

        return product.get();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(ProductDto product) {
        //이미 존재하는 상품 등록 시도시 예외 발생
        productRepository.findByContents(product).ifPresent((p) -> {
            throw new ProductAlreadyExistsException();
        });


        //상품 등록
        return productRepository.save(product);
    }

    public Product updateProductById(Long id, ProductDto product) {
        //존재하지 않는 상품 업데이트 시도시 예외 발생
        productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        //상품 업데이트
        return productRepository.update(id, product);
    }

    public void deleteProduct(Long id) {
        //존재하지 않는 상품 삭제 시도시 예외 발생
        productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        //상품 삭제
        productRepository.deleteById(id);
    }
}
