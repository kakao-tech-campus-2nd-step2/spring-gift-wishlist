package gift.service;

import gift.domain.repository.ProductJdbcRepository;
import gift.domain.model.Product;
import gift.domain.model.ProductDto;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductJdbcRepository productJdbcRepository;

    public ProductService(ProductJdbcRepository productJdbcRepository) {
        this.productJdbcRepository = productJdbcRepository;
    }

    public Product getProduct(Long id) {
        if (!productJdbcRepository.isExistProductId(id)) {
            throw new NoSuchElementException("Invalid Product ID");
        }
        return productJdbcRepository.getProductById(id);
    }

    public List<ProductDto> getAllProduct() {
        return productJdbcRepository.getAllProduct();
    }

    public void addProduct(ProductDto productDto) {
        productJdbcRepository.addProduct(productDto);
    }

    private boolean isValidLong(Object value) {
        try {
            Long.parseLong(value.toString());
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public void updateProduct(ProductDto productDto) {
        if (!productJdbcRepository.isExistProductId(productDto.getId())) {
            throw new NoSuchElementException("Invalid Product ID");
        }
        productJdbcRepository.updateProduct(productDto);
    }

    public void deleteProduct(Long id) {
        if (!productJdbcRepository.isExistProductId(id)) {
            throw new NoSuchElementException("Invalid Product ID");
        }
        productJdbcRepository.deleteProduct(id);
    }
}
