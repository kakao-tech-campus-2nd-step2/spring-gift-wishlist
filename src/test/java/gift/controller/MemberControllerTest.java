package gift.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import gift.dto.LoginRequest;
import gift.dto.RegisterRequest;
import gift.util.AuthUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("빈 이름으로 회원가입 요청하기")
    void registerFailWithEmptyName() throws Exception {
        var result = mockMvc.perform(post("/api/members/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new RegisterRequest("", "test@naver.com", "testPassword"))));

        result.andExpect(status().isBadRequest())
                .andExpect(content().string("이름의 길이는 최소 1자 이상이어야 합니다."));
    }

    @Test
    @DisplayName("이름의 길이가 8초과인 이용자의 회원가입 요청하기")
    void registerFailWithNameLength() throws Exception {
        var result = mockMvc.perform(post("/api/members/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new RegisterRequest("이름이8글자초과예요", "test@naver.com", "testPassword"))));

        result.andExpect(status().isBadRequest())
                .andExpect(content().string("이름의 길이는 8자를 초과할 수 없습니다."));
    }

    @Test
    @DisplayName("허용되지 않는 형식의 이메일로 회원가입 요청하기")
    void registerFailWithEmail() throws Exception {
        var result = mockMvc.perform(post("/api/members/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new RegisterRequest("테스트", "test@hello", "testPassword"))));

        result.andExpect(status().isBadRequest())
                .andExpect(content().string("허용되지 않은 형식의 이메일입니다."));
    }

    @Test
    @DisplayName("허용되지 않는 형식의 패스워드로 회원가입 요청하기")
    void registerFailWithPassword() throws Exception {
        var result = mockMvc.perform(post("/api/members/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new RegisterRequest("테스트", "test@naver.com", "잘못된패스워드"))));

        result.andExpect(status().isBadRequest())
                .andExpect(content().string("허용되지 않은 형식의 패스워드입니다."));
    }

    @Test
    @DisplayName("허용되지 않는 형식의 이메일로 로그인 요청하기")
    void loginFailWithEmail() throws Exception {
        var result = mockMvc.perform(post("/api/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequest("test@hello", "testPassword"))));

        result.andExpect(status().isBadRequest())
                .andExpect(content().string("허용되지 않은 형식의 이메일입니다."));
    }

    @Test
    @DisplayName("허용되지 않는 형식의 패스워드로 로그인 요청하기")
    void loginFailWithPassword() throws Exception {
        var result = mockMvc.perform(post("/api/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequest("test@naver.com", "잘못된패스워드"))));

        result.andExpect(status().isBadRequest())
                .andExpect(content().string("허용되지 않은 형식의 패스워드입니다."));
    }

    @Test
    @DisplayName("정상적으로 회원가입 후 로그인 요청하기")
    void registerAndLoginSuccess() throws Exception {
        var register = mockMvc.perform(post("/api/members/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new RegisterRequest("테스트", "test@naver.com", "testPassword"))));

        register.andExpect(status().isOk());

        var login = mockMvc.perform(post("/api/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequest("test@naver.com", "testPassword"))));

        login.andExpect(status().isOk());
    }

    @Test
    @DisplayName("정상적으로 회원가입 후 삭제하기")
    void registerAndDeleteSuccess() throws Exception {
        var register = mockMvc.perform(post("/api/members/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new RegisterRequest("테스트", "test@naver.com", "testPassword"))));

        var result = register.andExpect(status().isOk()).andReturn();
        var responseContent = result.getResponse().getContentAsString();
        var token = JsonPath.parse(responseContent).read("$.token");

        var id = AuthUtils.getMemberIdWithToken(token.toString());

        var deleted = mockMvc.perform(delete("/api/members/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token));

        deleted.andExpect(status().isNoContent());
    }
}
