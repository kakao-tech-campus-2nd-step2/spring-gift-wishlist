package gift.product.service;

import gift.product.dto.ProductDto;
import gift.product.model.Product;
import gift.product.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public static final String NAME_KAKAO = "카카오";
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductAll() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Product insertProduct(ProductDto productDto) {
        Product product = new Product(productDto.name(), productDto.price(), productDto.imageUrl());
        product = productRepository.save(product);

        return product;
    }

    public Product updateProduct(Long id, ProductDto productDTO) {
        Product product = new Product(id, productDTO.name(), productDTO.price(),
            productDTO.imageUrl());
        productRepository.update(product);
        return product;
    }

    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }
}
