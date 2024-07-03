package gift.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.service.ProductService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mvc;

    @DisplayName("[GET] 모든 상품 정보를 조회한다.")
    @Test
    void productList() throws Exception {
        //given
        given(productService.getProducts()).willReturn(List.of());

        //when
        ResultActions result = mvc.perform(get("/api/products"));

        //then
        result
            .andExpect(status().isOk());

        then(productService).should().getProducts();
    }

    @DisplayName("[GET] 하나의 상품 정보를 조회한다.")
    @Test
    void productOne() throws Exception {
        //given
        Long productId = 1L;
        ProductResponse product = new ProductResponse();

        given(productService.getProduct(productId)).willReturn(product);

        //when
        ResultActions result = mvc.perform(get("/api/products/{productId}", productId));

        //then
        result
            .andExpect(status().isOk());

        then(productService).should().getProduct(productId);
    }

    @DisplayName("[POST] 상품 하나를 추가한다.")
    @Test
    void productAdd() throws Exception {
        //given
        ProductRequest request = new ProductRequest();

        willDoNothing().given(productService).addProduct(request);

        //when
        ResultActions result = mvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(request)));

        //then
        result
            .andExpect(status().isCreated());

        then(productService).should().addProduct(request);
    }

    @DisplayName("[PUT] 상품 정보를 수정한다.")
    @Test
    void productEdit() throws Exception {
        //given
        Long productId = 1L;
        ProductRequest request = new ProductRequest();

        willDoNothing().given(productService).editProduct(productId, request);

        //when
        ResultActions result = mvc.perform(put("/api/products/{productId}", productId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)));

        //then
        result
            .andExpect(status().isOk());

        then(productService).should().editProduct(productId, request);
    }

    @DisplayName("[DELETE] 상품 하나를 삭제한다.")
    @Test
    void productRemove() throws Exception {
        //given
        Long productId = 1L;

        willDoNothing().given(productService).removeProduct(productId);

        //when
        ResultActions result = mvc.perform(delete("/api/products/{productId}", productId));

        //then
        result
            .andExpect(status().isOk());

        then(productService).should().removeProduct(productId);
    }

}
