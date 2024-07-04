package gift.product.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import gift.product.model.Product;
import gift.product.service.ProductService;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        model = new BindingAwareModelMap();
    }

    @Test
    public void testRegisterProduct() {
        Product product = new Product(1L, "Product1", 1, "image.url");
        when(productService.getAllProducts()).thenReturn(Collections.singletonList(product));

        String viewName = productController.registerProduct(1L, "Test Product", 100, "http://image.url", model);

        assertEquals("product", viewName);
        Collection<Product> products = (Collection<Product>) model.getAttribute("productList");
        assertEquals(1, products.size());
        verify(productService, times(1)).registerProduct(any(Product.class));
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product(1L, "Product1", 2, "image.url");
        when(productService.getAllProducts()).thenReturn(Collections.singletonList(product));

        String viewName = productController.updateProduct(1L, "Updated Product", 200, "http://image.url", model);

        assertEquals("product", viewName);
        Collection<Product> products = (Collection<Product>) model.getAttribute("productList");
        assertEquals(1, products.size());
        verify(productService, times(1)).updateProduct(any(Product.class));
    }

    @Test
    public void testDeleteProduct() {
        when(productService.getAllProducts()).thenReturn(Collections.emptyList());

        String viewName = productController.deleteProduct(1L, model);

        assertEquals("product", viewName);
        Collection<Product> products = (Collection<Product>) model.getAttribute("productList");
        assertEquals(0, products.size());
        verify(productService, times(1)).deleteProduct(1L);
    }
}
