package gift.service;

import gift.dto.ProductDTO;
import gift.model.Product;
import gift.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    public void testGetAllProducts() {
        Product product = new Product(1L, "Test Product", 100, "test.jpg");
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<ProductDTO> products = productService.getAllProducts();
        assertEquals(1, products.size());
        assertEquals("Test Product", products.get(0).name());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Test Product", 100, "test.jpg");
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDTO productDTO = productService.getProductById(1L);
        assertEquals("Test Product", productDTO.name());
    }

    @Test
    public void testAddProduct() {
        Product product = new Product(1L, "Test Product", 100, "test.jpg");
        ProductDTO productDTO = new ProductDTO(null, "Test Product", 100, "test.jpg");
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO createdProduct = productService.addProduct(productDTO);
        assertEquals("Test Product", createdProduct.name());
    }

    @Test
    public void testUpdateProduct() {
        Product existingProduct = new Product(1L, "Old Product", 100, "old.jpg");
        Product updatedProduct = new Product(1L, "Updated Product", 200, "updated.jpg");
        ProductDTO productDTO = new ProductDTO(1L, "Updated Product", 200, "updated.jpg");

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        ProductDTO result = productService.updateProduct(1L, productDTO);
        assertEquals("Updated Product", result.name());
        assertEquals(200, result.price());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product(1L, "Test Product", 100, "test.jpg");
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);
        verify(productRepository, times(1)).delete(1L);
    }
}
