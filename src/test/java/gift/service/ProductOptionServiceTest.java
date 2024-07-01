package gift.service;

import gift.dto.ProductRequest;
import gift.dto.ProductOptionRequest;
import gift.exception.PriceLessThanZeroException;
import gift.model.Product;
import gift.model.ProductOption;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductOptionServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductOptionService optionService;

    Product product;

    @BeforeEach
    @DisplayName("옵션에 대한 작업을 수행하기 위한 상품 추가 작업")
    void setUp() {
        ProductRequest productRequest = new ProductRequest("아이폰16pro", 1800000, "https://image.zdnet.co.kr/2024/03/21/29acda4f841885d2122750fbff5cbd9d.jpg");
        product = productService.addProduct(productRequest);
    }

    @AfterEach
    @DisplayName("다른 테스트에서 생성된 상품 옵션을 삭제하기 위한 작업")
    void tearDown() {
        productService.deleteProduct(product.getId());
    }

    @Test
    @DisplayName("정상 옵션 추가하기")
    void successOptionAdd() {
        ProductOptionRequest productOptionRequest = new ProductOptionRequest(product.getId(), "기본", 0);

        ProductOption savedOption = optionService.addOption(productOptionRequest);
        ProductOption findOption = optionService.getOption(savedOption.getId());
        Assertions.assertThat(findOption.getName()).isEqualTo("기본");
    }

    @Test
    @DisplayName("불량 옵션 추가하기")
    void failOptionAdd() {
        Assertions.assertThatThrownBy(() -> new ProductOptionRequest(product.getId(), "기본", -1000)).isInstanceOf(PriceLessThanZeroException.class);
    }

    @Test
    @DisplayName("둘 이상의 옵션 추가하기")
    void addOptions() {
        ProductOptionRequest normalOptionDto = new ProductOptionRequest(product.getId(), "기본", 0);
        ProductOptionRequest size255gbOptionDto = new ProductOptionRequest(product.getId(), "255gb", 100000);
        optionService.addOption(normalOptionDto);
        optionService.addOption(size255gbOptionDto);
        Assertions.assertThat(optionService.getOptions(product.getId()).size()).isEqualTo(2);
    }

    @Test
    @DisplayName("옵션 수정하기")
    void updateOption() {
        ProductOptionRequest productOptionRequest = new ProductOptionRequest(product.getId(), "기본", 0);

        ProductOption savedOption = optionService.addOption(productOptionRequest);

        ProductOptionRequest optionUpdateDto = new ProductOptionRequest(product.getId(), "노멀", 0);
        optionService.updateOption(savedOption.getId(),optionUpdateDto);

        ProductOption findOption = optionService.getOption(savedOption.getId());
        Assertions.assertThat(findOption.getName()).isNotEqualTo("기본");
        Assertions.assertThat(findOption.getName()).isEqualTo("노멀");
    }

    @Test
    @DisplayName("옵션 삭제하기")
    void deleteOption() {
        ProductOptionRequest productOptionRequest = new ProductOptionRequest(product.getId(), "기본", 0);
        ProductOption savedOption = optionService.addOption(productOptionRequest);

        Assertions.assertThat(optionService.getOptions(product.getId()).size()).isEqualTo(1);

        optionService.deleteOption(savedOption.getId());

        Assertions.assertThat(optionService.getOptions(product.getId()).size()).isEqualTo(0);
    }
}