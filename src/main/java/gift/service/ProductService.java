package gift.service;

import gift.controller.ProductRequest;
import gift.domain.Product;
import gift.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional()
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product register(ProductRequest productRequest){
        validateDuplicateProduct(productRequest);
        Product product = Product.RequestToEntity(productRequest);
        return productRepository.save(product);
    }
    private void validateDuplicateProduct(ProductRequest productRequest){
        productRepository.findByName(productRequest.getName())
                .ifPresent(p -> {
                    throw new IllegalStateException("이미 존재하는 상품입니다.");
                });
    }

    public List<Product> findProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> findOne(Long productId){
        return productRepository.findById(productId);
    }

    public Product update(Long productId, ProductRequest productRequest){
        Optional<Product> product = productRepository.updateById(productId, productRequest);
        if (product.isPresent()){
            return product.get();
        };
        throw new NoSuchElementException("존재하지 않는 상품입니다.");

    }

    public Optional<Product> delete(Long productId){
        return productRepository.deleteById(productId);
    }
}
