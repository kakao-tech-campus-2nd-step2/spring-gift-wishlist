package gift.service;

import static gift.util.Constants.INVALID_PRICE;
import static gift.util.Constants.PRODUCT_NOT_FOUND;
import static gift.util.ProductMapper.toDTO;
import static gift.util.ProductMapper.toEntity;

import gift.dto.ProductDTO;
import gift.exception.InvalidProductPriceException;
import gift.exception.ProductNotFoundException;
import gift.model.Product;
import gift.repository.ProductRepository;
import gift.util.ProductMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
            .map(ProductMapper::toDTO)
            .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
            .map(ProductMapper::toDTO)
            .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND + id));
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        validatePrice(productDTO.price());
        Product product = toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return toDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND + id));
        validatePrice(productDTO.price());
        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        product.setImageURL(productDTO.imageUrl());
        Product updatedProduct = productRepository.save(product);
        return toDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND + id);
        }
        productRepository.delete(id);
    }

    private static void validatePrice(int price) {
        if (price < 0) {
            throw new InvalidProductPriceException(INVALID_PRICE);
        }
    }
}
