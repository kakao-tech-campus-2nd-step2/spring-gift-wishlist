package gift.domain.product.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("상품 생성 성공")
    void create_success() throws Exception {
        mockMvc.perform(post("/products")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("name", "탕종 블루베리 베이글")
            .param("price", "3500")
            .param("imageUrl", "https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004823]_20230911131337469.jpg"))
            .andExpect(status().isFound())
            .andExpect(redirectedUrlPattern("/products/{spring:[0-9]+}"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("상품 생성 시 이름이 NULL인 경우")
    void create_fail_type_error() throws Exception {
        mockMvc.perform(post("/products")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("price", "3500")
            .param("imageUrl", "https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004823]_20230911131337469.jpg"))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/products/new"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("상품 수정 성공")
    void update_success() throws Exception {
        mockMvc.perform(put("/products/1")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("name", "탕종 블루베리 베이글")
            .param("price", "3500")
            .param("imageUrl", "https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004823]_20230911131337469.jpg"))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/products/1"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("상품 수정 시 이름이 NULL인 경우")
    void update_fail_type_error() throws Exception {
        mockMvc.perform(put("/products/1")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("price", "3500")
            .param("imageUrl", "https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004823]_20230911131337469.jpg"))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/products/edit/1"))
            .andExpect(status().is3xxRedirection());
    }
}