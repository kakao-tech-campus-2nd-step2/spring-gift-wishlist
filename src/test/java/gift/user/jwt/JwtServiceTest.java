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
        String role = "USER";
        String token = jwtService.createToken(email, role);
        assertNotNull(token);  // 토큰이 생성되었는지 확인
        System.out.println("Generated Token: " + token);
    }

    @Test
    public void testGetEmailAndRoleFromToken() {
        String email = "user@example.com";
        String role = "USER";
        String token = jwtService.createToken(email, role);

        String extractedEmail = jwtService.getEmailFromToken(token);
        String extractedRole = jwtService.getRoleFromToken(token);

        System.out.println("Extracted Email: " + extractedEmail);
        System.out.println("Extracted Role: " + extractedRole);

        assertEquals(email, extractedEmail);
        assertEquals(role, extractedRole);
    }
}
