package gift.db;

import gift.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JdbcTest
@Import(ProductH2DB.class)
class ProductH2DBTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("h2Database")
    private ProductH2DB productH2DB;

    @Test
    void testAddProduct() throws Exception {
        //given
        Product product = new Product(5L, "Espresso", 3000, "https://example.com/espresso.jpg");
        productH2DB.addProduct(product);

        //when
        Product retrievedProduct = productH2DB.getProduct(5L);

        //then
        assertEquals("Espresso", retrievedProduct.getName());
        assertEquals(3000, retrievedProduct.getPrice());
        assertEquals("https://example.com/espresso.jpg", retrievedProduct.getImageUrl());
    }

    @Test
    void testGetProduct() {
        //when
        Product product = productH2DB.getProduct(1L);

        //then
        assertNotNull(product);
        assertEquals("Americano", product.getName());
        assertEquals(4500, product.getPrice());
    }

    @Test
    void testGetProducts() {
        //when
        List<Product> products = productH2DB.getProducts();

        //then
        assertEquals(4, products.size());
    }

    @Test
    void testRemoveProduct() {
        //when
        productH2DB.removeProduct(1L);
        List<Product> products = productH2DB.getProducts();

        //then
        assertEquals(3, products.size());
    }

    @Test
    void testRemoveProducts() {
        //when
        productH2DB.removeProducts(List.of(1L, 2L));
        List<Product> products = productH2DB.getProducts();

        //then
        assertEquals(2, products.size());
    }

    @Test
    void testEditProduct() {
        //given
        Product updatedProduct = new Product(1L, "Americano Grande", 5000, "https://example.com/americano_grande.jpg");
        //when
        productH2DB.editProduct(updatedProduct);
        Product retrievedProduct = productH2DB.getProduct(1L);

        //then
        assertEquals("Americano Grande", retrievedProduct.getName());
        assertEquals(5000, retrievedProduct.getPrice());
        assertEquals("https://example.com/americano_grande.jpg", retrievedProduct.getImageUrl());
    }
}
