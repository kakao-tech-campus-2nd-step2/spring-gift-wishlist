package gift.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.controller.product.ProductController;
import gift.dto.Product;
import gift.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProductNameValidateTest {

    @MockBean
    private ProductService productDB;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(productDB)).build();
        this.objectMapper = new ObjectMapper();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "abc", "abcd", "15자aaaaaaaaaaaa", "    햇반      ", "[단독] 고급 지갑", "커피&우유", "1+1 제품", "/바로/구매___", "-_- 안사면 후회"})
    @DisplayName("유효한 상품 이름 검증 TEST")
    void lengthTest(String name) throws Exception {
        Product product = new Product(name, 10000, "imageUrl");
        String json = objectMapper.writeValueAsString(product);
        productDB.addProduct(product);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/products").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }

    @ParameterizedTest
    @ValueSource(strings = {"!!!", "저렴한 우유!!", "@멘션", "#더샵", "진짜~~~~", "카카오톡", "리얼 카카오 우유", "카카오카카오", "진짜카카오100", "공백              포함               ", "aaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
    @DisplayName("비유효한 상품 이름 검증 TEST")
    void t2(String name) throws Exception {
        Product product = new Product(name, 10000, "imageUrl");
        String json = objectMapper.writeValueAsString(product);
        productDB.addProduct(product);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/products").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());
    }

}
