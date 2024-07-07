package gift.service;

import gift.exception.ProductAlreadyExistsException;
import gift.exception.ResourceNotFoundException;
import gift.model.Product;
import gift.repository.ProductRepository;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void addProduct(Product product) {
        if (isExistProduct(product)) {
            throw new ProductAlreadyExistsException("Product already exist");
        }
        if (isInvalidProduct(product)) {
            throw new IllegalArgumentException("Invalid product attribute");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        productRepository.insertProduct(product);
    }

    public Product getProduct(Long id) {
        return productRepository.selectProduct(id);
    }

    public Optional<Product> retreiveProduct(Long id) {
        var product = getProduct(id);
        if (product == null) {
            return Optional.empty();
        }
        if (!getAllProducts().containsKey(id)) {
            throw new NoSuchElementException("Product Not Found");
        }
        return Optional.of(getAllProducts().get(id));
    }

    public Map<Long, Product> getAllProducts() {
        return productRepository.selectAllProducts();
    }

    @Transactional
    public void updateProductDetail(Product product) {
        if (isInvalidProduct(product)) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (!productRepository.existsById(product.getId())) {
            throw new ResourceNotFoundException("Product not found with id: " + product.getId());
        }
        productRepository.updateProduct(product);
    }

    @Transactional
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
        productRepository.deleteProduct(id);
    }

    public boolean isProductsRepositoryEmpty() {
        return productRepository.selectAllProducts().isEmpty();
    }


    public boolean isExistProduct(Product product) {
        return productRepository.existsById(product.getId());
    }


    public boolean isInvalidProduct(Product newProduct) {
        return newProduct.getId() == null || newProduct.getId() < 0 || newProduct.getName()
            .isEmpty()
            || newProduct.getPrice() < 0
            || newProduct.getImageUrl().isEmpty();
    }

}