package gift;

import static org.assertj.core.api.Assertions.assertThat;

import gift.model.Product;
import gift.service.ProductService;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        List<Product> products = productService.findAllProducts();
        for (Product product : products) {
            productService.deleteProduct(product.getId());
        }
    }

    @Test
    @DisplayName("조회 test")
    public void findAllTest() {
        Product pencil = new Product(1L, "연필", 2000, "www.pencil.com");
        Product eraser = new Product(2L, "지우개", 4000, "www.eraser.com");

        productService.addProduct(pencil);
        productService.addProduct(eraser);

        List<Product> products = productService.findAllProducts();

        assertThat(products).hasSize(2);
        assertThat(products).extracting(Product::getId)
            .contains(pencil.getId(), eraser.getId());
    }

    @Test
    @DisplayName("추가 test")
    public void addProductTest() {
        Product pencil = new Product(1L, "연필", 2000, "www.pencil.com");

        Long productId = productService.addProduct(pencil);

        assertThat(productId).isEqualTo(pencil.getId());
    }

}