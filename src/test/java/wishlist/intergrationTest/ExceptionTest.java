package wishlist.intergrationTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wishlist.model.item.ItemForm;

@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource({
        "'카카오김치', 2000, 'url'",
        "'김치%', 2000, 'url'",
        "'123456789ABCDEFG', 2000, 'url'",
        "'', '2000', 'url'",
        "'김치', ,'url'",
        "'김치', -1, 'url'",
    })
    @DisplayName("상품등록실패")
    void createItemFailure(String name, Long price, String imgUrl) throws Exception {
        ItemForm form = new ItemForm(name, price, imgUrl);
        mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("item", form))
            .andExpect(status().isBadRequest())
            .andDo(print());
    }

    @Test
    @DisplayName("상품 조회 실패(조회 요청이 없음으로 수정요청으로 확인)")
    void findItemFailure() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/update/10"))
            .andExpect(status().isNotFound())
            .andDo(print());
    }

    @Test
    @DisplayName("존재 하지 않는 요청 시도")
    void attemptInvalidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/asdf"))
            .andExpect(status().isBadRequest())
            .andDo(print());
    }

    @Test
    @DisplayName("지원하지 않는 HTTP 메서드 요청")
    void attemptInvalidHttpMethodRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/delete/1"))
            .andExpect(status().isMethodNotAllowed())
            .andDo(print());
    }
}
