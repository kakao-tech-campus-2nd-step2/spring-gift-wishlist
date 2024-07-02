package gift;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
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
        mockMvc.perform(get("/api/products/product"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("상품 추가 테스트")
    void addProduct() throws Exception {
        String requestJson = """
            {"id": 10,"name": "커피", "price": 5500,"imageUrl": "https://..."}
            """;

        mockMvc.perform(post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("상품 수정 폼 페이지 테스트")
    void editProductForm() throws Exception {
        String requestJson = """
            {"id": 10,"name": "커피", "price": 5500,"imageUrl": "https://..."}
            """;

        mockMvc.perform(post("/api/products/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson));

        mockMvc.perform(get("/api/products/product/10"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("상품 수정 테스트")
    void editProduct() throws Exception {
        String requestJson1 = """
            {"id": 10,"name": "커피", "price": 5500,"imageUrl": "https://..."}
            """;
        String requestJson2 = """
            {"id": 10,"name": "달다구리 커피", "price": 6500,"imageUrl": "https://..."}
            """;

        mockMvc.perform(post("/api/products/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson1));

        mockMvc.perform(put("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson2))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    void deleteProduct() throws Exception {
        String requestJson = """
            {"id": 10,"name": "커피", "price": 5500,"imageUrl": "https://..."}
            """;

        mockMvc.perform(post("/api/products/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson));

        mockMvc.perform(delete("/api/products/product/10"))
            .andExpect(status().isOk());
    }

    @DisplayName("상품명 유효성 검증 성공 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"커피", "coffee", "1234cof피", "커피(예가체프)", "커피[아무거나]",
        "커+ffee", "012345678901234", "커&피", "(커/피]", "(커][[fee))()", "+-&커__()fe&/_"})
    void addProductSuccess(String name) throws Exception {
        String requestJson = String.format("""
            {"id": 10,"name": "%s", "price": 5500,"imageUrl": "https://..."}
            """, name);
        mockMvc.perform(post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andExpect(status().isOk());

    }

    @DisplayName("상품명 유효성 검증 실패 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "0123456789012345", "커피{블랙}", "커@피", "커피(카카오)",
        "카카오 선물", "이건카카오커피", "커피😀", "커피커피커피커피커피커피커피커피커피"})
    void addProductError(String name) throws Exception {
        String requestJson = String.format("""
            {"id": 10,"name": "%s", "price": 5500,"imageUrl": "https://..."}
            """, name);
        mockMvc.perform(post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andExpect(status().isNotFound());

    }
}