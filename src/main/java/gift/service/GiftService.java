package gift.service;

import gift.controller.dto.ProductDTO;
import gift.domain.Product;
import gift.repository.ProductRepository;
import gift.utils.error.ProductAlreadyExistsException;
import gift.utils.error.ProductNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GiftService {

    private final ProductRepository productRepository;

    public GiftService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(Long id) {
        Product byId = productRepository.findById(id);
        if (byId == null) {
            throw new ProductNotFoundException("Product NOT FOUND");
        }
        return byId;
    }

    public List<Product> getAllProduct() {
        List<Product> ALL = productRepository.findAll();
        if (ALL == null) {
            throw new ProductNotFoundException("Product NOT FOUND");
        }
        return ALL;
    }

    public ProductDTO postProducts(ProductDTO productDTO) {
        vaildateProductName(productDTO.getName());
        Product product = new Product(productDTO.getId(), productDTO.getName(),
            productDTO.getPrice(), productDTO.getImageUrl());

        boolean b = productRepository.create(product);
        if (!b) {
            throw new ProductAlreadyExistsException("Product EXIST");
        }
        return productDTO;
    }

    public ProductDTO putProducts(ProductDTO productDTO, Long id) {
        vaildateProductName(productDTO.getName());
        Product product = new Product(productDTO.getId(), productDTO.getName(),
            productDTO.getPrice(), productDTO.getImageUrl());

        boolean update = productRepository.update(product, id);
        if (!update) {
            throw new ProductNotFoundException("Product NOT FOUND");
        }
        return productDTO;
    }

    public Long deleteProducts(Long id) {
        boolean delete = productRepository.delete(id);
        if (!delete) {
            throw new ProductNotFoundException("Product NOT FOUND");
        }
        return id;
    }

    private void vaildateProductName(String name) {
        if (name.replace(" ", "").contains("카카오")) {
            throw new IllegalArgumentException("\"카카오\"가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있다.");
        }
    }

}
