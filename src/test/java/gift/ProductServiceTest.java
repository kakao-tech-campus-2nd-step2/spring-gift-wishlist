package gift;

import static org.assertj.core.api.Assertions.assertThat;

import gift.domain.Product;
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
        List<Product> products = productService.findAll();
        for (Product product : products) {
            productService.deleteProduct(product.getId());
        }
    }

    @Test
    @DisplayName("전건조회 테스트")
    public void findAllTest() {
        Product chicken = new Product(1L, "치킨", 20000, "Chicken.com");
        Product pizza = new Product(2L, "피자", 30000, "Pizza.com");

        productService.addProduct(chicken);
        productService.addProduct(pizza);

        List<Product> products = productService.findAll();

        assertThat(products).hasSize(2);
        assertThat(products).extracting(Product::getId)
            .contains(chicken.getId(), pizza.getId());
    }

    @Test
    @DisplayName("상품추가 테스트")
    public void addProductTest() {
        Product chicken = new Product(1L, "치킨", 20000, "Chicken.com");

        Long productId = productService.addProduct(chicken);

        assertThat(productId).isEqualTo(chicken.getId());
    }

    @Test
    @DisplayName("상품수정 테스트")
    public void updateProductTest() {
        Product chicken = new Product(1L, "치킨", 20000, "Chicken.com");
        productService.addProduct(chicken);
        chicken.setName("수정치킨");
        Long productId = productService.updateProduct(chicken);

        assertThat(productId).isEqualTo(chicken.getId());
        assertThat(productService.getProduct(chicken.getId()).getName()).isEqualTo("수정치킨");

    }

    @Test
    @DisplayName("상품삭제 테스트")
    public void deleteProductTest() {
        Product chicken = new Product(1L, "치킨", 20000, "Chicken.com");
        productService.addProduct(chicken);
        Long productId = productService.deleteProduct(1L);

        assertThat(productId).isEqualTo(1L);
        assertThat(productService.getProduct(1L)).isNull();
    }



}
