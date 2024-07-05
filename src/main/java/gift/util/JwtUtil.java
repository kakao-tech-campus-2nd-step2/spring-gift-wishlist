package gift.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;

@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  private String secretKey;

  private Key key;

  @PostConstruct
  public void init() {
    key = Keys.hmacShaKeyFor(secretKey.getBytes());
  }

  public String generateToken(Long memberId, String email) {
    return Jwts.builder()
            .setSubject(memberId.toString())
            .claim("email", email)
            .signWith(key)
            .compact();
  }
}
