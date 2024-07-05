package gift.service;

import gift.dto.ProductRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @AfterEach
    @DisplayName("상품 레포지토리 초기화하기")
    void clearAll() {
        var products = service.getProducts();
        for (var product : products) {
            service.deleteProduct(product.id());
        }
    }

    @Test
    @DisplayName("정상 상품 추가하기")
    void addProductSuccess() {
        var productRequest = new ProductRequest("상품1", 10000, "이미지 주소");
        var savedProduct = service.addProduct(productRequest);

        Assertions.assertThat(savedProduct.name()).isEqualTo("상품1");
    }

    @Test
    @DisplayName("상품 수정하기")
    void updateProduct() {
        var productRequest = new ProductRequest("상품1", 10000, "이미지 주소");
        var savedProduct = service.addProduct(productRequest);
        var id = savedProduct.id();
        var updateDto = new ProductRequest("상품1", 7000, "이미지 주소2");

        service.updateProduct(id, updateDto);

        var updatedProduct = service.getProduct(id);
        Assertions.assertThat(updatedProduct.price()).isEqualTo(7000);
    }

    @Test
    @DisplayName("상품 삭제하기")
    void deleteProduct() {
        var productRequest = new ProductRequest("상품1", 10000, "이미지 주소");
        var savedProduct = service.addProduct(productRequest);

        Assertions.assertThat(service.getProducts().size()).isEqualTo(1);

        var id = savedProduct.id();
        service.deleteProduct(id);

        Assertions.assertThat(service.getProducts().size()).isEqualTo(0);
    }
}
