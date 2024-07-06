package gift.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import gift.dto.LoginRequest;
import gift.dto.WishProductAddRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WishProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private String managerToken;
    private String memberToken;

    @BeforeEach
    void setAccessToken() throws Exception {
        var managerLogin = mockMvc.perform(post("/api/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequest("admin@naver.com", "password"))));

        var managerResult = managerLogin.andExpect(status().isOk()).andReturn();
        var managerResponseContent = managerResult.getResponse().getContentAsString();
        var managerJWT = JsonPath.parse(managerResponseContent).read("$.token");
        managerToken = managerJWT.toString();

        var memberLogin = mockMvc.perform(post("/api/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequest("member@naver.com", "password"))));

        var memberResult = memberLogin.andExpect(status().isOk()).andReturn();
        var memberResponseContent = memberResult.getResponse().getContentAsString();
        var memberJWT = JsonPath.parse(memberResponseContent).read("$.token");
        memberToken = memberJWT.toString();
    }

    @Test
    @DisplayName("잘못된 수량으로 된 위시 리스트 상품 추가하기")
    void addWishProductFailWithZeroCount() throws Exception {
        var wishProductAddRequest = new WishProductAddRequest(1L,0);
        var result = mockMvc.perform(post("/api/wishes/add")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + memberToken)
                .content(objectMapper.writeValueAsString(wishProductAddRequest)));

        result.andExpect(status().isBadRequest())
                .andExpect(content().string("상품의 수량은 반드시 1개 이상이어야 합니다."));
    }
}
