package gift;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import gift.prodcut.ProductController;
import gift.prodcut.ProductDAO;
import gift.prodcut.ProductService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    // 웹 계층 테스트
    private MockMvc mockMvc;

    @MockBean
    ProductDAO productDAO;

    @MockBean
    ProductService productService;

    @Test
    public void testPostProduct() throws Exception {
        // product 추가
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\",\"name\":\"Test Product\",\"price\":30, \"imageUrl\":\"https://www.youtube.com/watch?v=dQw4w9WgXcQ\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAllproduct() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProduct() throws Exception {
        // product 추가
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3,\"name\":\"Test Product\",\"price\":30, \"imageUrl\":\"https://www.youtube.com/watch?v=dQw4w9WgXcQ\"}"))
                .andExpect(status().isCreated());

        // 추가된 product 조회하여 테스트
        mockMvc.perform(get("/api/products/3"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateProduct() throws Exception {
        // product 추가
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3,\"name\":\"Test Product\",\"price\":30, \"imageUrl\":\"https://www.youtube.com/watch?v=dQw4w9WgXcQ\"}"));

        // product 업데이트
        mockMvc.perform(put("/api/products/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3,\"name\":\"Update Product\",\"price\":999, \"imageUrl\":\"https://www.youtube.com/watch?v=dQw4w9WgXcQ\"}"))
                .andExpect(status().isOk());

        //업데이트 된 product를 조회하여 테스트
        mockMvc.perform(get("/api/products/3"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProduct() throws Exception {
        // product 추가
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Product\",\"price\":30}"));

        // 삭제하여 테스트
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());
    }
}