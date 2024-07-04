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
        Product chicken = new Product(1L, "치킨", 20000, "Chicken.com");
        Product pizza = new Product(2L, "피자", 30000, "Pizza.com");

        productService.addProduct(chicken);
        productService.addProduct(pizza);

        List<Product> products = productService.findAllProducts();

        assertThat(products).hasSize(2);
        assertThat(products).extracting(Product::getId)
            .contains(chicken.getId(), pizza.getId());
    }

    @Test
    @DisplayName("추가 test")
    public void addProductTest() {
        Product chicken = new Product(1L, "치킨", 20000, "Chicken.com");

        Long productId = productService.addProduct(chicken);

        assertThat(productId).isEqualTo(chicken.getId());
    }

}