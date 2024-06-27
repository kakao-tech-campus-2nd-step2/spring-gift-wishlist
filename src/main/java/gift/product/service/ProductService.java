package gift.product.service;

import gift.product.dto.ServiceDto;
import gift.product.entity.Product;
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

    public Product createProduct(ServiceDto serviceDto) {
        return productRepository.save(serviceDto.toProduct());
    }

    public Product updateProduct(ServiceDto serviceDto) {
        validateProductExists(serviceDto.id());
        return productRepository.save(serviceDto.toProduct());
    }

    public void deleteProduct(Long id) {
        validateProductExists(id);
        productRepository.deleteById(id);
    }

    private void validateProductExists(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException();
        }
    }
}
