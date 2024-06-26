package gift.repository;

import gift.model.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryTest {
    private final ProductRepository productRepository = new ProductRepository();

    @Test
    public void testSaveAndFindById() {
        Product product = new Product(null, "Test Product", 100, "test.jpg");
        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());
        assertTrue(foundProduct.isPresent());
        assertEquals("Test Product", foundProduct.get().getName());
    }

    @Test
    public void testFindAll() {
        Product product1 = new Product(null, "Product 1", 100, "prod1.jpg");
        Product product2 = new Product(null, "Product 2", 200, "prod2.jpg");
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> products = productRepository.findAll();
        assertEquals(2, products.size());
    }

    @Test
    public void testDelete() {
        Product product = new Product(null, "Test Product", 100, "test.jpg");
        Product savedProduct = productRepository.save(product);
        productRepository.delete(savedProduct.getId());

        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());
        assertFalse(foundProduct.isPresent());
    }
}
