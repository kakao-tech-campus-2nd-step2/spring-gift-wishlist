package gift.auth.jwt;

import gift.domain.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    private static final long ACCESSTOKEN_EXPIRATION_TIME = 30 * 60 * 1000;

    private String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public Token generateToken(User user) {

        long now = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(now + ACCESSTOKEN_EXPIRATION_TIME);

        return new Token(Jwts.builder()
            .subject(user.getId().toString())
            .claim("name", user.getName())
            .claim("email", user.getEmail())
            .claim("role", user.getRole())
            .expiration(accessTokenExpiresIn)
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact());
    }
}
