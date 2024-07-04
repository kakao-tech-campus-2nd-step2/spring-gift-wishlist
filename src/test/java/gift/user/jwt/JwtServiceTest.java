package gift.user.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    public void testCreateToken() {
        String email = "user@example.com";
        String token = jwtService.createToken(email);
        assertNotNull(token);  // 토큰이 생성되었는지 확인
        System.out.println("Generated Token: " + token);
    }

    @Test
    public void testGetEmailFromToken() {
        String email = "user@example.com";
        String token = jwtService.createToken(email);
        String extractedEmail = jwtService.getEmailFromToken(token);
        assertNotNull(extractedEmail);  // 추출된 이메일이 null이 아닌지 확인
        System.out.println("Extracted Email: " + extractedEmail);
        assertEquals(email, extractedEmail);  // 추출된 이메일이 원래의 이메일과 일치하는지 확인
    }
}
