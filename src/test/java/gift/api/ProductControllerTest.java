package gift.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.application.ProductService;
import gift.domain.Product;
import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    ProductService productService;

    @Test
    @DisplayName("상품 전체 조회 기능 테스트")
    void getAllProducts() throws Exception {
        List<ProductResponse> response = new ArrayList<>();
        ProductResponse productResponse1 = new ProductResponse(new Product(new ProductRequest(1L, "product1", 1000, "https://testshop.com")));
        ProductResponse productResponse2 = new ProductResponse(new Product(new ProductRequest(2L, "product2", 3000, "https://testshop.com")));
        response.add(productResponse1);
        response.add(productResponse2);
        String responseJson = objectMapper.writeValueAsString(response);
        when(productService.getAllProducts()).thenReturn(response);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson))
                .andDo(print());

        verify(productService).getAllProducts();
    }

    @Test
    @DisplayName("상품 상세 조회 기능 테스트")
    void getProduct() throws Exception {
        ProductResponse response = new ProductResponse(new Product(new ProductRequest(1L, "product1", 1000, "https://testshop.com")));
        String responseJson = objectMapper.writeValueAsString(response);
        when(productService.getProductById(any())).thenReturn(response);

        mockMvc.perform(get("/api/products/{id}", response.id()))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson))
                .andExpect(jsonPath("$.id").value(response.id()))
                .andExpect(jsonPath("$.name").value(response.name()))
                .andExpect(jsonPath("$.price").value(response.price()))
                .andExpect(jsonPath("$.imageUrl").value(response.imageUrl()))
                .andDo(print());

        verify(productService).getProductById(response.id());
    }

    @Test
    @DisplayName("상품 추가 기능 테스트")
    void addProduct() throws Exception {
        ProductRequest request = new ProductRequest(1L, "product1", 1000, "https://testshop.com");
        ProductResponse response = new ProductResponse(new Product(request));
        String requestJson = objectMapper.writeValueAsString(request);
        String responseJson = objectMapper.writeValueAsString(response);
        when(productService.createProduct(any(ProductRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson))
                .andExpect(jsonPath("$.id").value(response.id()))
                .andExpect(jsonPath("$.name").value(response.name()))
                .andExpect(jsonPath("$.price").value(response.price()))
                .andExpect(jsonPath("$.imageUrl").value(response.imageUrl()))
                .andDo(print());

        verify(productService).createProduct(any(ProductRequest.class));
    }

    @Test
    @DisplayName("단일 상품 삭제 기능 테스트")
    void deleteProduct() throws Exception {
        Long productId = 1L;
        ProductResponse response = new ProductResponse(new Product(new ProductRequest(1L, "product1", 1000, "https://testshop.com")));
        when(productService.deleteProductById(productId)).thenReturn(productId);

        mockMvc.perform(delete("/api/products/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(productId)))
                .andDo(print());

        verify(productService).deleteProductById(productId);
    }

    @Test
    @DisplayName("상품 전체 삭제 기능 테스트")
    void deleteAllProducts() throws Exception {
        mockMvc.perform(delete("/api/products"))
                .andExpect(status().isOk())
                .andDo(print());

        verify(productService).deleteAllProducts();
    }

    @Test
    @DisplayName("상품 수정 기능 테스트")
    void updateProduct() throws Exception {
        Long productId = 2L;
        ProductRequest request = new ProductRequest(2L, "product2", 3000, "https://testshop.com");
        ProductResponse response = new ProductResponse(new Product(request));
        String requestJson = objectMapper.writeValueAsString(request);
        when(productService.updateProduct(productId, request)).thenReturn(productId);

        mockMvc.perform(patch("/api/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(productId)))
                .andDo(print());

        verify(productService).updateProduct(productId, request);
    }
}