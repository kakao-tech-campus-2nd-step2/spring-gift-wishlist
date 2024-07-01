package gift.service;

import gift.dto.ProductDTO;
import gift.repository.H2Repository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final H2Repository repository;

    @Autowired
    public ProductService(H2Repository h2Repository) {
        this.repository = h2Repository;
    }

    public Collection<ProductDTO> getProducts() {
        return repository.getProducts();
    }

    public ProductDTO getProduct(Long id) {
        return repository.getProduct(id);
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        long id = repository.addProduct(productDTO);
        return repository.getProduct(id);
    }

    public ProductDTO updateProduct(long id, ProductDTO productDTO) {
        if (repository.getProduct(id) == null) {
            throw new IllegalArgumentException("해당 상품을 수정할 수 없습니다.");
        }
        ProductDTO updatedProduct = new ProductDTO(id, productDTO.name(), productDTO.price(), productDTO.imageUrl());
        repository.updateProduct(updatedProduct);
        return updatedProduct;
    }

    public ProductDTO deleteProduct(long id) {
        ProductDTO deletedProduct = repository.getProduct(id);
        if (deletedProduct == null) {
            throw new IllegalArgumentException("해당 상품을 삭제할 수 없습니다.");
        }
        repository.deleteProduct(id);
        return deletedProduct;
    }
}
