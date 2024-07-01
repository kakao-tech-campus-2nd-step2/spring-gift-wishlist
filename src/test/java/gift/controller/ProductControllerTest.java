package gift.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.dto.ProductRequest;
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
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("잘못된 가격으로 된 오류 상품 생성하기")
    void addProductFailWithPrice() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/products/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProductRequest("상품1", -1000, "이미지 주소"))));

        result.andExpect(status().isBadRequest()).andExpect(content().string("잘못된 입력입니다."));
    }

    @Test
    @DisplayName("이름의 길이가 15초과인 오류 상품 생성하기")
    void addProductFailWithNameLength() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/products/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProductRequest("햄버거햄버거햄버거햄버거햄버거햄", 1000, "이미지 주소"))));

        result.andExpect(status().isBadRequest()).andExpect(content().string("잘못된 입력입니다."));
    }

    @Test
    @DisplayName("정상 상품 생성하기 - 특수문자 포함")
    void addProductSuccessWithSpecialChar() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/products/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProductRequest("햄버거()[]+-&/_", 1000, "이미지 주소"))));

        result.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("오류 상품 생성하기 - 허용되지 않은 특수문자 포함")
    void addProductFailWithSpecialChar() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/products/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProductRequest("햄버거()[]+-&/_**", 1000, "이미지 주소"))));

        result.andExpect(status().isBadRequest()).andExpect(content().string("잘못된 입력입니다."));
    }
}