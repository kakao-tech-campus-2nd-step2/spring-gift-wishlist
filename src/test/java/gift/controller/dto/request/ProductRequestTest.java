package gift.controller.dto.request;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductRequestTest {
    private static ValidatorFactory factory;
    private static Validator validator;
    private static final String ERROR_MESSAGE_NAME_REQUIRED = "상품명은 필수 입력값입니다.";
    private static final String ERROR_MESSAGE_NAME_LENGTH = "상품명은 최대 15자까지 입력 가능합니다.";
    private static final String ERROR_MESSAGE_PRICE_REQUIRED = "가격은 필수 입력값입니다.";
    private static final String ERROR_MESSAGE_PRICE_RANGE = "가격은 1원 이상, 21억원 이하이어야 합니다.";
    private static final String ERROR_MESSAGE_IMG_URL_REQUIRED = "이미지 URL은 필수 입력값입니다.";
    private static final String ERROR_MESSAGE_IMG_URL_FORMAT = "이미지 URL 형식이 올바르지 않습니다.";

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("ProductRequest 생성 테스트[정상]")
    void createProductRequest() {
        // given
        String name = "테스트 상품";
        Integer price = 1000;
        String imgUrl = "http://test.com";

        // when
        ProductRequest productRequest = new ProductRequest(name, price, imgUrl);
        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(productRequest);

        // then
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("ProductRequest 생성 테스트[name이 빈 문자열인 경우]")
    void createProductRequestWithNameBlank() {
        // given
        String name = "";
        Integer price = 1000;
        String imgUrl = "http://test.com";

        // when
        ProductRequest productRequest = new ProductRequest(name, price, imgUrl);
        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(productRequest);

        // then
        assertEquals(1, violations.size());
        assertEquals(ERROR_MESSAGE_NAME_REQUIRED, violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("ProductRequest 생성 테스트[name이 null 경우]")
    void createProductWithNameNull() {
        // given
        String name = null;
        Integer price = 1000;
        String imgUrl = "http://test.com";

        // when
        ProductRequest productRequest = new ProductRequest(name, price, imgUrl);
        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(productRequest);

        // then
        assertEquals(1, violations.size());
        assertEquals(ERROR_MESSAGE_NAME_REQUIRED, violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("ProductRequest 생성 테스트[name이 15자 이상일 경우]")
    void createProductWithNameLengthOver15() {
        // given
        String name = "1234567890123456";
        Integer price = 1000;
        String imgUrl = "http://test.com";

        // when
        ProductRequest productRequest = new ProductRequest(name, price, imgUrl);
        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(productRequest);

        // then
        assertEquals(1, violations.size());
        assertEquals(ERROR_MESSAGE_NAME_LENGTH, violations.iterator().next().getMessage());
    }
}