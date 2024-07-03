package gift;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebController.class)
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductController productController;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(1L, "Product1", 100, "https://image.nongshim.com/non/pro/1647822565539.jpg", Collections.singletonList(new Option(1L,"Option1",10)));
    }

    // 상품 목록 페이지 테스트
    @Test
    void testViewProductPage() throws Exception {

        List<Product> products = Collections.singletonList(product);
        Mockito.when(productController.getAllProducts()).thenReturn(new ResponseEntity<>(products, HttpStatus.OK));

        // GET 요청
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/index"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attribute("products", products));
    }

    // 상품 추가 페이지 테스트
    @Test
    void testShowNewProductForm() throws Exception {
        // GET 요청
        mockMvc.perform(get("/products/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/new"))
                .andExpect(model().attributeExists("product"));
    }

    // 상품 저장 테스트
    @Test
    void testSaveProduct() throws Exception {

        Mockito.when(productController.addProduct(any(Product.class))).thenReturn(new ResponseEntity<>(product, HttpStatus.CREATED));

        // POST 요청을 보내고 리디렉션 상태 및 URL 검증
        mockMvc.perform(post("/products")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
    }

    // 상품 수정 페이지 테스트
    @Test
    void testShowEditProductForm() throws Exception {

        Mockito.when(productController.getAllProducts()).thenReturn(new ResponseEntity<>(Collections.singletonList(product), HttpStatus.OK));

        // GET 요청
        mockMvc.perform(get("/products/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/edit"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", product));
    }

    // 상품 수정 테스트
    @Test
    void testUpdateProduct() throws Exception {

        Mockito.when(productController.updateProduct(anyLong(), any(Product.class))).thenReturn(new ResponseEntity<>(product, HttpStatus.OK));

        // POST 요청을 보내고 리디렉션 상태 및 URL 검증
        mockMvc.perform(post("/products/1")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
    }

    // 상품 삭제 테스트
    @Test
    void testDeleteProduct() throws Exception {

        Mockito.when(productController.deleteProduct(anyLong())).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        // GET 요청
        mockMvc.perform(get("/products/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
    }
}
