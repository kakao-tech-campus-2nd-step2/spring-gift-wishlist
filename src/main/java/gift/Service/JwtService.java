package gift.Service;

import gift.DTO.JwtToken;
import gift.DTO.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private String key = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

  public JwtToken createAccessToken(UserDto userDto) {
    Instant now = Instant.now();
    Instant expiresAt = now.plus(1, ChronoUnit.DAYS); // 현재 시각에서 1일 뒤로 만료 설정

    String accessToken = Jwts.builder()
      .setHeaderParam("typ", "Bearer") // 토큰 타입을 지정
      .setSubject(userDto.getEmail())
      .claim("email", userDto.getEmail())
      .claim("pw", userDto.getPw())
      .setExpiration(Date.from(expiresAt))
      .signWith(Keys.hmacShaKeyFor(key.getBytes()), SignatureAlgorithm.HS256)
      .compact();

    return new JwtToken(accessToken);
  }

  public boolean isValidToken(JwtToken jwtToken) {
    JwtParser jwtParser = Jwts.parser()
      .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
      .build();
    try {
      Jws<Claims> claims = jwtParser.parseClaimsJws(jwtToken.getAccessToken());
      return !claims.getBody().getExpiration().before(new Date());
    } catch (JwtException e) {
      return false;
    }
  }

  public UserDto getUserEmailFromToken(String token) {
    JwtParser jwtParser = Jwts.parser()
      .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
      .build();

    Jws<Claims> claims = jwtParser.parseClaimsJws(token);
    String email = claims.getBody().get("email", String.class);
    String pw = claims.getBody().get("pw", String.class);
    return new UserDto(email, pw);
  }
}
