package gift;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

import gift.product.dto.ProductDto;
import gift.product.model.Product;
import gift.product.service.ProductService;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductTest {

    private final ProductService productService;

    @Autowired
    public ProductTest(ProductService productService) {
        this.productService = productService;
    }

    @AfterEach
    public void productsInit() {
        List<Product> products = productService.getProductAll();
        for (Product product : products) {
            productService.deleteProduct(product.getId());
        }
    }

    @Test
    public void insertProductTest() {
        ProductDto productDTO = new ProductDto("사과", 3000, "사진링크");
        Product product = productService.insertProduct(productDTO);

        assertSoftly(softly -> {
                assertThat(product.getName()).isEqualTo("사과");
                assertThat(product.getPrice()).isEqualTo(3000);
                assertThat(product.getImageUrl()).isEqualTo("사진링크");
        });
    }

    @Test
    public void getProductTest() {
        ProductDto productDTO = new ProductDto("사과", 3000, "사진링크");
        Product insertedProduct = productService.insertProduct(productDTO);

        Product product = productService.getProduct(insertedProduct.getId());

        assertSoftly(softly -> {
            assertThat(product.getName()).isEqualTo("사과");
            assertThat(product.getPrice()).isEqualTo(3000);
            assertThat(product.getImageUrl()).isEqualTo("사진링크");
        });

    }

    @Test
    public void getProductAllTest() {
        ProductDto productDTO = new ProductDto("사과", 3000, "사진링크");
        productService.insertProduct(productDTO);

        List<Product> productAll = productService.getProductAll();

        assertSoftly(softly -> {
            assertThat(productAll.get(0).getName()).isEqualTo("사과");
            assertThat(productAll.get(0).getPrice()).isEqualTo(3000);
            assertThat(productAll.get(0).getImageUrl()).isEqualTo("사진링크");
        });
    }

    @Test
    public void updateProduct() {
        ProductDto productDTO = new ProductDto("사과", 3000, "사진링크");
        Product product = productService.insertProduct(productDTO);

        ProductDto productUpdatedDTO = new ProductDto("사과", 5500, "사진링크2");

        Product productUpdated = productService.updateProduct(product.getId(), productUpdatedDTO);

        assertSoftly(softly -> {
            assertThat(productUpdated.getName()).isEqualTo("사과");
            assertThat(productUpdated.getPrice()).isEqualTo(5500);
            assertThat(productUpdated.getImageUrl()).isEqualTo("사진링크2");
        });
    }

    @Test
    public void deleteProduct() {
        ProductDto productDTO = new ProductDto("사과", 3000, "사진링크");
        productService.insertProduct(productDTO);

        productDTO = new ProductDto("바나나", 1500, "사진링크2");
        Product product = productService.insertProduct(productDTO);

        productService.deleteProduct(product.getId());

        List<Product> productAll = productService.getProductAll();
        assertThat(productAll).hasSize(1);
    }
}
