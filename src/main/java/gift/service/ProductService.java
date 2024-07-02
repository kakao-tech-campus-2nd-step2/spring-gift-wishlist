package gift.service;

import gift.domain.Product;
import gift.dto.RequestProductDto;
import gift.dto.ResponseProductDto;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ResponseProductDto getProduct(Long id) {
        Product product = productRepository.getProductById(id);
        return ResponseProductDto.from(product);
    }

    public Long createProduct(RequestProductDto requestProductDto) {
        Product product = requestProductDto.toProduct();
        return productRepository.saveProduct(product);
    }

    public Long updateProduct(RequestProductDto requestProductDto, Long id) {
        Product product = requestProductDto.toProduct();
        return productRepository.updateProduct(id, product);

    }

    public Long deleteProduct(Long id) {
        return productRepository.deleteProductById(id);
    }

    public List<ResponseProductDto> getAllProducts() {
        List<Product> products = productRepository.getAllProducts();
        return ResponseProductDto.of(products);
    }

    public void deleteProducts(List<Long> productIds) {
        productRepository.deleteProductByIdList(productIds);
    }
}
