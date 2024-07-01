package gift.service;

import gift.dto.ProductRequest;
import gift.exception.PriceLessThanZeroException;
import gift.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService service;

    @AfterEach
    @DisplayName("상품 레포지토리 초기화하기")
    void clearAll() {
        List<Product> products = service.getProducts();
        for (var product : products) {
            service.deleteProduct(product.getId());
        }
    }

    @Test
    @DisplayName("정상 상품 추가하기")
    void addProductSuccess() {
        ProductRequest productRequest = new ProductRequest("상품1", 10000, "이미지 주소");
        Product savedProduct = service.addProduct(productRequest);
        Assertions.assertThat(savedProduct.getName()).isEqualTo("상품1");
    }

    @Test
    @DisplayName("오류 상품 생성하기")
    void addProductFail() {
        Assertions.assertThatThrownBy(() -> new ProductRequest("상품1", -1000, "이미지 주소"))
                .isInstanceOf(PriceLessThanZeroException.class);
    }

    @Test
    @DisplayName("상품 수정하기")
    void updateProduct() {
        ProductRequest productRequest = new ProductRequest("상품1", 10000, "이미지 주소");
        Product savedProduct = service.addProduct(productRequest);

        Long id = savedProduct.getId();
        ProductRequest updateDto = new ProductRequest("상품1", 7000, "이미지 주소2");

        service.updateProduct(id, updateDto);

        Product updatedProduct = service.getProduct(id);
        Assertions.assertThat(updatedProduct.getPrice()).isEqualTo(7000);
    }

    @Test
    @DisplayName("상품 삭제하기")
    void deleteProduct() {
        ProductRequest productRequest = new ProductRequest("상품1", 10000, "이미지 주소");
        Product savedProduct = service.addProduct(productRequest);

        Assertions.assertThat(service.getProducts().size()).isEqualTo(1);

        Long id = savedProduct.getId();
        service.deleteProduct(id);

        Assertions.assertThat(service.getProducts().size()).isEqualTo(0);
    }
}