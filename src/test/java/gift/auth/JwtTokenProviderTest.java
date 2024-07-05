package gift.auth;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtTokenProviderTest {

    JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expirationSeconds}")
    private int expirationSeconds;

    @Test
    void printPrivateInfo() {
        System.out.println("secretKey = " + secretKey);
        System.out.println("expirationSeconds = " + expirationSeconds);
    }

}