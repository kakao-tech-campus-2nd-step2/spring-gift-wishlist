package gift.db;

import gift.dto.Product;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductMemoryDBTest {

//    static ProductMemoryDB productMemoryDB;
//
//    ProductMemoryDBTest() {
//        productMemoryDB = new ProductMemoryDB();
//    }
//
//    @Test
//    void testAddProduct() throws Exception {
//        //given
//        Product newProduct = new Product(5L, "Mocha", 6000, "https://example.com/mocha.png");
//
//        //when
//        productMemoryDB.addProduct(newProduct);
//
//        //then
//        Product retrievedProduct = productMemoryDB.getProduct(5L);
//        assertEquals(newProduct, retrievedProduct);
//    }
//
//    @Test
//    void testGetProduct() {
//        //when
//        Product product = productMemoryDB.getProduct(1L);
//
//        //then
//        assertNotNull(product);
//        assertEquals(1L, product.getId());
//        assertEquals("Americano", product.getName());
//    }
//
//    @Test
//    void testGetProducts() {
//        List<Product> products = productMemoryDB.getProducts();
//        assertEquals(4, products.size());
//    }
//
//    @Test
//    void testRemoveProduct() {
//        try {
//            productMemoryDB.removeProduct(1L);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        assertNull(productMemoryDB.getProduct(1L));
//    }
//
//    @Test
//    void testRemoveProducts() {
//        List<Long> productIds = Arrays.asList(1L, 2L);
//        productMemoryDB.removeProducts(productIds);
//
//        assertNull(productMemoryDB.getProduct(1L));
//        assertNull(productMemoryDB.getProduct(2L));
//        assertNotNull(productMemoryDB.getProduct(3L));
//        assertNotNull(productMemoryDB.getProduct(4L));
//    }
//
////    @Test
////    void testEditProduct() throws Exception {
////        Product updatedProduct = new Product(1L, "Espresso", 5000, "https://example.com/espresso.png");
////        productMemoryDB.(updatedProduct);
////
////        Product retrievedProduct = productMemoryDB.getProduct(1L);
////        assertEquals(updatedProduct, retrievedProduct);
////    }
//
//    @Test
//    void testHasProductId() {
//        assertTrue(productMemoryDB.hasProductId(1L));
//        assertFalse(productMemoryDB.hasProductId(999L));
//    }

}
