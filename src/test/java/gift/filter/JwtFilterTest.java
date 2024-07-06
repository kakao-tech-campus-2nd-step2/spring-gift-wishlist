package gift.filter;

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
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRlc3RAbmF2ZXIuY29tIn0.KImiqe-VpxLzKmgLyrUBnI2m2hikArHpV3NAYvdu86Y";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/wishlist")
                        .header("Authorization", Vars.TokenPrefix + token))
                .andExpect(status().isOk());
    }

    @Test
    void testRequestWithInvalidToken() throws Exception {
        String token = "abcdefg.hijklmnopqr.stuvwxyz";
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


