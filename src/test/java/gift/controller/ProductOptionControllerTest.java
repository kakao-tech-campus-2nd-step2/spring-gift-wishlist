package gift.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.dto.ProductOptionRequest;
import gift.dto.ProductRequest;
import gift.model.Product;
import gift.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductOptionControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductService productService;

    private Product product;

    @BeforeEach
    @DisplayName("옵션에 대한 작업을 수행하기 위한 상품 추가 작업")
    void setUp() {
        ProductRequest productRequest = new ProductRequest("아이폰16pro", 1800000, "https://image.zdnet.co.kr/2024/03/21/29acda4f841885d2122750fbff5cbd9d.jpg");
        product = productService.addProduct(productRequest);
    }

    @Test
    @DisplayName("잘못된 가격으로 된 오류 상품 옵션 생성하기")
    void failOptionAdd() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/options/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProductOptionRequest(product.getId(), "기본", -1000))));

        result.andExpect(status().isBadRequest()).andExpect(content().string("추가 금액은 0보다 크거나 같아야 합니다."));
    }

    @Test
    @DisplayName("빈 이름을 가진 오류 상품 옵션 생성하기")
    void failOptionAddWithEmptyName() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/options/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProductOptionRequest(product.getId(), "", 1000))));

        result.andExpect(status().isBadRequest()).andExpect(content().string("이름의 길이는 최소 1자 이상이어야 합니다."));
    }

    @Test
    @DisplayName("정상 상품 옵션 생성하기")
    void failOptionSuccess() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/options/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProductOptionRequest(product.getId(), "Large", 1500))));

        result.andExpect(status().isCreated());
    }
}
