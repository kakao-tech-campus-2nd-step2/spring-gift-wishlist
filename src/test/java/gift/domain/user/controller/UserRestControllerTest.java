package gift.domain.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@AutoConfigureMockMvc
@SpringBootTest
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String REGISTER_URL = "/api/users/register";
    private static final String LOGIN_URL = "/api/users/login";

    private MockHttpServletRequestBuilder postRequest(String url, String content) {
        return post(url)
            .content(content)
            .contentType(MediaType.APPLICATION_JSON);
    }


    @Test
    void create() throws Exception {
        String user = "{ \"name\": \"testUser\", \"email\": \"test@test.com\", \"password\": \"test123\" }";
        mockMvc.perform(postRequest(REGISTER_URL, user))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.token").exists())
            .andDo(print());
    }

    @Test
    @DisplayName("이미 존재하는 이메일로 가입 시도")
    void create_fail() throws Exception {
        String user1 = "{ \"name\": \"testUser2\", \"email\": \"test2@test.com\", \"password\": \"test123\" }";
        String user2 = "{ \"name\": \"testUser2\", \"email\": \"test2@test.com\", \"password\": \"test123\" }";

        mockMvc.perform(postRequest(REGISTER_URL, user1))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.token").exists());

        mockMvc.perform(postRequest(REGISTER_URL, user2))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("중복된 이메일입니다."));
    }

    @Test
    void login() throws Exception {
        String user = "{ \"name\": \"testUser3\", \"email\": \"test3@test.com\", \"password\": \"test123\" }";
        mockMvc.perform(postRequest(REGISTER_URL, user))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.token").exists())
            .andDo(print());

        String login = "{ \"email\": \"test3@test.com\", \"password\": \"test123\" }";
        mockMvc.perform(postRequest(LOGIN_URL, login))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.token").exists())
            .andDo(print());
    }

    @Test
    @DisplayName("틀린 비밀번호로 로그인 시도")
    void login_fail() throws Exception {
        String user = "{ \"name\": \"testUser4\", \"email\": \"test4@test.com\", \"password\": \"test123\" }";
        mockMvc.perform(postRequest(REGISTER_URL, user))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.token").exists())
            .andDo(print());

        String login = "{ \"email\": \"test4@test.com\", \"password\": \"test456\" }";
        mockMvc.perform(postRequest(LOGIN_URL, login))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("잘못된 비밀번호입니다."));
    }
}