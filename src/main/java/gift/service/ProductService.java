package gift.service;

import gift.dto.ProductDTO;
import gift.model.Product;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getImageURL());
    }
}
