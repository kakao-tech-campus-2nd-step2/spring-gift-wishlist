package gift.product.service;

import gift.product.dto.ProductServiceDto;
import gift.product.domain.Product;
import gift.product.exception.ProductNotFoundException;
import gift.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    public void createProduct(ProductServiceDto productServiceDto) {
        productRepository.save(productServiceDto.toProduct());
    }

    public void updateProduct(ProductServiceDto productServiceDto) {
        validateProductExists(productServiceDto.id());
        productRepository.save(productServiceDto.toProduct());
    }

    public void deleteProduct(Long id) {
        validateProductExists(id);
        productRepository.deleteById(id);
    }

    // validate 과정에서 repository로 부터 받은 값을 사용하지 않도록 코드를 작성하였다
    // 때문에, The return value of "orElseThrow" must be used. 경고가 발생한다
    // 동작을 이해하고 작성한 코드이기에, 이 경고는 무시해도 된다고 생각하지만,
    // 일반적인 validate 함수들도 이와 같이 orElseThrow 를 사용하여 만들까?
    private void validateProductExists(Long id) {
        productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }
}
