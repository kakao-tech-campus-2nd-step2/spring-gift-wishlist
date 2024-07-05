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
        Long id = 1L;
        String role = "USER";
        String token = jwtService.createToken(id, role);
        assertNotNull(token);  // 토큰이 생성되었는지 확인
        System.out.println("Generated Token: " + token);
    }

    @Test
    public void testGetEmailAndRoleFromToken() {
        Long id = 1L;
        String role = "USER";
        String token = jwtService.createToken(id, role);

        Long extractedId = jwtService.getIdFromToken(token);
        String extractedRole = jwtService.getRoleFromToken(token);

        System.out.println("Extracted Id: " + extractedId);
        System.out.println("Extracted Role: " + extractedRole);

        assertEquals(id, extractedId);
        assertEquals(role, extractedRole);
    }
}
