package gift.product.service;

import gift.core.product.Product;
import gift.core.product.ProductRepository;
import gift.core.product.ProductService;
import gift.core.product.exception.ProductAlreadyExistsException;
import gift.core.product.exception.ProductNotFoundException;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product get(Long id) {
        Product product = productRepository.get(id);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return product;
    }

    @Override
    public boolean exists(Long id) {
        return productRepository.exists(id);
    }

    @Override
    public void createProduct(@Nonnull Product product) {
        if (productRepository.exists(product.id())) {
            throw new ProductAlreadyExistsException();
        }
        productRepository.save(product);
    }

    @Override
    public void updateProduct(@Nonnull Product product) {
        if (!productRepository.exists(product.id())) {
            throw new ProductNotFoundException();
        }
        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        if (!productRepository.exists(id)) {
            throw new ProductNotFoundException();
        }
        productRepository.remove(id);
    }
}
