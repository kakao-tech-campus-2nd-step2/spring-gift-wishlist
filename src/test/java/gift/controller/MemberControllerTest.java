package gift.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.dto.RegisterRequest;
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
}
