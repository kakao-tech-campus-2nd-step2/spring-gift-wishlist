package gift.domain.product.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@SpringBootTest
class ProductRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() throws Exception {
        String product = "{ \"name\": \"탕종 블루베리 베이글\", \"price\": \"3500\", \"imageUrl\": \"https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004823]_20230911131337469.jpg\" }";
        mockMvc.perform(post("/api/products")
            .content(product)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andDo(print());
    }

    @Test
    void readAll() throws Exception {
        mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void readById() throws Exception {
        mockMvc.perform(get("/api/products/1"))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void update() throws Exception {
        String product = "{ \"name\": \"탕종 블루베리 베이글\", \"price\": \"3500\", \"imageUrl\": \"https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004823]_20230911131337469.jpg\" }";
        mockMvc.perform(put("/api/products/1")
            .content(product)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/2"))
            .andExpect(status().isNoContent())
            .andDo(print());
    }

    @Test
    @DisplayName("상품 생성 시 이름이 NULL인 경우")
    void create_fail_null_error() throws Exception {
        String product = "{ \"price\": \"3500\", \"imageUrl\": \"https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004823]_20230911131337469.jpg\" }";
        mockMvc.perform(post("/api/products")
            .content(product)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.name", Is.is("상품 이름은 필수 입력 필드이며 공백으로만 구성될 수 없습니다.")));
    }

    @Test
    @DisplayName("상품 생성 시 가격이 int형으로 변환 불가능한 경우")
    void create_fail_price_type_error() throws Exception {
        String product = "{ \"name\": \"탕종 블루베리 베이글\", \"price\": \"삼천오백원\", \"imageUrl\": \"https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004823]_20230911131337469.jpg\" }";
        mockMvc.perform(post("/api/products")
            .content(product)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("잘못된 형식입니다. 상품 가격을 숫자로 입력해주세요."));
    }

    @Test
    @DisplayName("상품 수정 시 이름에 \"카카오\"가 포함")
    void update_fail_name_kakao_error() throws Exception {
        String product = "{ \"name\": \"카카오빵\", \"price\": \"3500\", \"imageUrl\": \"https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004823]_20230911131337469.jpg\" }";
        mockMvc.perform(put("/api/products/1")
            .content(product)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.name", Is.is("\"카카오\"가 포함된 문구는 담당 MD와 협의 후 사용 가능합니다.")));
    }
}