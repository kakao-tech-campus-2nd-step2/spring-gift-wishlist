package gift;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import gift.controller.ProductController;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

public class ProductControllerTest {

    private ProductController productController;

    @BeforeEach
    public void setup() {
        productController = new ProductController();
    }

    @Test
    @DisplayName("상품 추가 테스트")
    public void createProductTest() {
        // givne
        Product product = new Product(1L, "아이스 카페 아메리카노 T", 4500L, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");

        // when
        ResponseEntity<Product> createdProductResponse = productController.createProduct(product);
        Product createdProduct = createdProductResponse.getBody();


        // then
        assertThat(createdProductResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createdProduct.getId()).isEqualTo(1L);
        assertThat(createdProduct.getName()).isEqualTo("아이스 카페 아메리카노 T");
        assertThat(createdProduct.getPrice()).isEqualTo(4500L);
        assertThat(createdProduct.getImageUrl()).isEqualTo("https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
    }

    @Test
    @DisplayName("모든 상품 조회 테스트")
    public void getAllProductsTest() {
        // given
        Product product1 = new Product(1L, "아이스 카페 아메리카노 T", 4500L, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        Product product2 = new Product(2L, "아이스 카페라떼 T", 3500L, "https://market.shosyn.com/assets/data/product/_product_image_2368.jpeg?u=1568979271");

        // when
        productController.createProduct(product1);
        productController.createProduct(product2);
        Collection<Product> products = productController.getAllProducts();

        // then
        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("특정 id의 상품 조회 테스트")
    public void getProductByIdTest() {
        // given
        Product product1 = new Product(1L, "아이스 카페 아메리카노 T", 4500L, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        Product product2 = new Product(2L, "아이스 카페라떼 T", 3500L, "https://market.shosyn.com/assets/data/product/_product_image_2368.jpeg?u=1568979271");

        // when
        productController.createProduct(product1);
        productController.createProduct(product2);
        ResponseEntity<Product> createdProductResponse = productController.getProductById(1L);
        Product createdProduct = createdProductResponse.getBody();

        // then
        assertThat(createdProduct.getId()).isEqualTo(1L);
        assertThat(createdProduct.getName()).isEqualTo("아이스 카페 아메리카노 T");
        assertThat(createdProduct.getPrice()).isEqualTo(4500L);
        assertThat(createdProduct.getImageUrl()).isEqualTo("https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
    }

    @Test
    public void updateProductTest() {
        // given
        Product product1 = new Product(1L, "아이스 카페 아메리카노 T", 4500L, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        Product product2 = new Product(2L, "아이스 카페라떼 T", 3500L, "https://market.shosyn.com/assets/data/product/_product_image_2368.jpeg?u=1568979271");

        // when
        productController.createProduct(product1);
        ResponseEntity<Product> updatedProductResponse = productController.updateProduct(1L, product2);
        Product updatedProduct = updatedProductResponse.getBody();

        // then
        assertThat(updatedProduct.getName()).isEqualTo("아이스 카페라떼 T");
        assertThat(updatedProduct.getPrice()).isEqualTo(3500L);
        assertThat(updatedProduct.getImageUrl()).isEqualTo("https://market.shosyn.com/assets/data/product/_product_image_2368.jpeg?u=1568979271");

    }

    @Test
    public void deleteProductTest() {
        // given
        Product product1 = new Product(1L, "아이스 카페 아메리카노 T", 4500L, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");

        // when
        productController.createProduct(product1);
        productController.deleteProduct(1L);

        // then
        assertThat(productController.getAllProducts().size()).isEqualTo(0);
        assertThat(productController.getProductById(1L).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
