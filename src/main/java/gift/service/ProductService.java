package gift.service;

import gift.dto.ProductDTO;
import gift.dto.ProductsDTO;
import gift.model.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductDTO productDTO) {
        productRepository.insertToTable(new Product(
                productDTO.id(),
                productDTO.name(),
                productDTO.price(),
                productDTO.imageUrl()
        ));
    }

    public ProductsDTO getAllProducts() {
        return new ProductsDTO(productRepository.selectAllProducts());
    }


    public void updateProduct(Long id, ProductDTO productDTO) {
        productRepository.updateToTable(id, new Product(
                productDTO.id(),
                productDTO.name(),
                productDTO.price(),
                productDTO.imageUrl()));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteToTable(id);
    }
}
