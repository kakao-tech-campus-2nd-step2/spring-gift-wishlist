package gift.service;

import gift.entity.Product;
import gift.repository.JdbcProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final JdbcProductRepository repository;

    @Autowired
    public ProductService(JdbcProductRepository repository) {
        this.repository = repository;
    }


    public boolean saveProduct(Product product) {
        long productId = product.id();
        if(!repository.findById(productId)
                .isPresent()) {
            repository.save(product);
            return true;
        }
        return false;
    }

    public Optional<Product> getProduct(long productId) {
        return repository.findById(productId);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public boolean deleteProduct(long productId) {
        if(repository.findById(productId)
                .isPresent()) {
            repository.deleteById(productId);
            return true;
        }
        return false;
    }

    public boolean updateProduct(long id, Product product) {
        long productId = product.id();
        if(repository.findById(productId)
                .isPresent()) {
            repository.updateById(productId, product);
            return true;
        }
        return false;
    }

}
