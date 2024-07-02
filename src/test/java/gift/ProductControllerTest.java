package gift;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    private @Autowired MockMvc mockMvc;

    @Test
    @DisplayName("상품 목록 가져오기 테스트")
    void getProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("상품 추가 폼 페이지 가져오기 테스트")
    void addProductForm() throws Exception {
        mockMvc.perform(get("/api/products/add"))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("상품 추가 테스트")
    void addProduct() throws Exception {
        mockMvc.perform(post("/api/products/add")
                .param("id", "10")
                .param("name", "커피")
                .param("price", "4500")
                .param("imageUrl",
                    "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
    }

    @Test
    @DisplayName("상품 수정 폼 페이지 테스트")
    void editProductForm() throws Exception {
        mockMvc.perform(post("/api/products/add")
            .param("id", "10")
            .param("name", "커피")
            .param("price", "4500")
            .param("imageUrl",
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));

        mockMvc.perform(get("/api/products/10"))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("상품 수정 테스트")
    void editProduct() throws Exception {
        mockMvc.perform(post("/api/products/add")
            .param("id", "10")
            .param("name", "커피")
            .param("price", "4500")
            .param("imageUrl",
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));

        mockMvc.perform(post("/api/products/edit")
                .param("id", "10")
                .param("name", "식혜")
                .param("price", "8900")
                .param("imageUrl",
                    "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    void deleteProduct() throws Exception {
        mockMvc.perform(post("/api/products/add")
            .param("id", "10")
            .param("name", "커피")
            .param("price", "4500")
            .param("imageUrl",
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));

        mockMvc.perform(post("/api/products/10"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
    }
}