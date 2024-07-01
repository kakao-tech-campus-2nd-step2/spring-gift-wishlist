package gift.service;

import gift.controller.dto.ProductDTO;
import gift.domain.Product;
import gift.repository.ProductRepository;
import gift.utils.error.ProductExistException;
import gift.utils.error.ProductNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GiftService {
    private final ProductRepository productRepository;

    public GiftService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(Long id){
        Product byId = productRepository.findById(id);
        if (byId == null){
            throw new ProductNotFoundException("Product NOT FOUND");
        }
        return byId;
    }
    public List<Product> getAllProduct(){
        List<Product> ALL = productRepository.findAll();
        if (ALL == null){
            throw new ProductNotFoundException("Product NOT FOUND");
        }
        return ALL;
    }

    public String postProducts(ProductDTO productDTO){
        String s = productRepository.create(productDTO);
        if (s == null){
            throw new ProductExistException("Product EXIST");
        }
        return s;
    }

    public String putProducts(ProductDTO productDTO,Long id){
        String s = productRepository.update(productDTO,id);
        if (s == null){
            throw new ProductNotFoundException("Product NOT FOUND");
        }
        return s;
    }
    public String deleteProducts(Long id){
        String s = productRepository.delete(id);
        if (s == null){
            throw new ProductNotFoundException("Product NOT FOUND");
        }
        return s;
    }
}