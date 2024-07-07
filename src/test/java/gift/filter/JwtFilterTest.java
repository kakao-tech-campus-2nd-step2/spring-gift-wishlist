package gift.filter;

import gift.model.User;
import gift.util.UserUtility;
import gift.util.Vars;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class JwtFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRequestWithValidToken() throws Exception {
        String email = "test@naver.com";
        String password = "test";
        User user = new User(email, password);

        String token = UserUtility.makeAccessToken(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/wishlist")
                        .header("Authorization", Vars.TokenPrefix + token))
                .andExpect(status().isOk());
    }

    @Test
    void testRequestWithInvalidToken() throws Exception {
        String email = "test@naver.com";
        String password = "test";
        User user = new User(email, password);

        String token = UserUtility.makeAccessToken(user)+"abcde";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/wishlist")
                        .header("Authorization", Vars.TokenPrefix + token))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testRequestWithEmptyToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/wishlist"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testRequestToPermittedUrlWithoutPermisson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(status().isOk());
    }
}


