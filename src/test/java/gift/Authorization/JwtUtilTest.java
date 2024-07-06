package gift.Authorization;

import gift.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {
    private JwtUtil jwtUtil;
    private User testUser;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil();
        testUser = new User();
        testUser.setEmail("user@example.com");
        testUser.setType("1"); // Assuming 'type' is an integer field in the User class
    }

    @Test
    public void testGenerateToken() {
        String token = jwtUtil.generateToken(testUser);
        assertNotNull(token);
        assertTrue(token.startsWith("eyJ")); // Check if token is a valid JWT (usually starts with 'eyJ')
    }

    @Test
    public void testExtractClaims() {
        String token = jwtUtil.generateToken(testUser);
        Claims claims = jwtUtil.extractClaims(token);
        assertEquals(testUser.getEmail(), claims.getSubject());
        assertEquals(testUser.getEmail(), claims.get("email"));
        assertEquals(testUser.getType(), claims.get("type"));
    }

    @Test
    public void testGetAuthentication() {
        String token = jwtUtil.generateToken(testUser);
        System.out.println(token);
        String type = jwtUtil.getUserType(token);
        assertEquals(testUser.getType(), type);
    }

    @Test
    public void testValidToken() {
        String token = jwtUtil.generateToken(testUser);
        assertTrue(jwtUtil.ValidToken(token, testUser));
    }

    @Test
    public void testInvalidToken() {
        String invalidToken = "invalid.token.here";
        assertThrows(JwtException.class, () -> {
            jwtUtil.getUserType(invalidToken);
        });

        assertFalse(jwtUtil.ValidToken(invalidToken, testUser));
    }

    @Test
    public void testExpiredToken() throws InterruptedException {
        JwtUtil shortLivedJwtUtil = new JwtUtil() {
            @Override
            public String generateToken(User user) {
                Date now = new Date();
                return Jwts.builder()
                        .setSubject(user.getEmail())
                        .claim("email", user.getEmail())
                        .claim("type", user.getType())
                        .setIssuedAt(now)
                        .setExpiration(new Date(System.currentTimeMillis() + 1000)) // 1 second for quick expiry
                        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                        .compact();
            }
        };

        String token = shortLivedJwtUtil.generateToken(testUser);
        Thread.sleep(2000); // Sleep for 2 seconds to ensure the token expires

        assertFalse(jwtUtil.ValidToken(token, testUser));
    }
}
