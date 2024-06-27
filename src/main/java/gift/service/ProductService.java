package gift.service;

import gift.dto.ProductDTO;
import gift.exception.InvalidProductPriceException;
import gift.exception.ProductNotFoundException;
import gift.model.Product;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private static ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getImageURL());
    }

    private static Product convertToEntity(ProductDTO productDTO) {
        validatePrice(productDTO.price());
        return new Product(productDTO.id(), productDTO.name(), productDTO.price(), productDTO.imageUrl());
    }

    private static void validatePrice(int price) {
        if (price < 0) {
            throw new InvalidProductPriceException("가격은 0 이상으로 설정되어야 합니다.");
        }
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
            .map(ProductService::convertToDTO)
            .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
            .map(ProductService::convertToDTO)
            .orElseThrow(() -> new ProductNotFoundException("상품을 다음의 id로 찾을 수 없습니다. id: " + id));
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("상품을 다음의 id로 찾을 수 없습니다. id: " + id));
        validatePrice(productDTO.price());
        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        product.setImageURL(productDTO.imageUrl());
        Product updatedProduct = productRepository.save(product);
        return convertToDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("상품을 다음의 id로 찾을 수 없습니다. id: " + id);
        }
        productRepository.delete(id);
    }
}
