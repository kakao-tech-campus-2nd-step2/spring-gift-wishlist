package gift.domain.service;

import gift.domain.Entity.Product;
import gift.domain.exception.ProductAlreadyExistsException;
import gift.domain.exception.ProductNotExistsException;
import gift.domain.repository.ProductRepository;
import gift.domain.dto.ProductResponseDto;
import gift.domain.dto.ProductRequestDto;
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

    public List<ProductResponseDto> findAll() {
        return productRepository.findAll().stream()
            .map(ProductResponseDto::of)
            .toList();
    }

    public ProductResponseDto find(Long id) {
        Optional<Product> product =  productRepository.findById(id);
        product.orElseThrow(ProductNotExistsException::new);
        return ProductResponseDto.of(product.get());
    }

    public ProductResponseDto save(ProductRequestDto product) {
        productRepository.findByValues(product).ifPresent(p -> {
            throw new ProductAlreadyExistsException();
        });
        return ProductResponseDto.of(productRepository.save(product));
    }

    public ProductResponseDto update(Long id, ProductRequestDto product) {
        productRepository.findById(id).orElseThrow(ProductNotExistsException::new);
        return ProductResponseDto.of(productRepository.update(id, product));
    }

    public void delete(Long id) {
        productRepository.findById(id).orElseThrow(ProductNotExistsException::new);
        productRepository.delete(id);
    }
}
