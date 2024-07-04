package gift;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import gift.dao.UserDAO;
import gift.dto.AuthRequest;
import gift.entity.User;
import gift.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TokenService tokenService;

    @BeforeEach
    public void setUp() {
        // 유저를 데이터베이스에 추가합니다.
        User user = new User();
        user.setEmail("admin@email.com");
        user.setPassword("password");
        userDAO.save(user);
    }

    @Test
    public void testLoginAndAccessProtectedResource() throws Exception {
        // 로그인 요청
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("admin@email.com");
        authRequest.setPassword("password");

        String response = mockMvc.perform(post("/login/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"admin@email.com\", \"password\":\"password\"}"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        // 응답에서 토큰을 추출합니다.
        String token = response.split(":")[1].replaceAll("[\"}]", "").trim();

        // 보호된 리소스에 접근 요청
        mockMvc.perform(get("/protected")
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk());
    }
}
