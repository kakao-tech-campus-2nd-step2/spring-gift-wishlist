package gift.util;

import gift.dto.UserDTO;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;


public class JwtUtil {
    private static final String secret = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
    private static final JwtParser jwtParser = Jwts.parser().setSigningKey(secret);

    public static String extractEmail(String token){
        return jwtParser
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static String extractUsername(String token){
        return jwtParser
                .parseClaimsJws(token)
                .getBody()
                .get("name", String.class);
    }

    public static String generateToken(UserDTO userDTO) {
        return Jwts.builder()
                .setSubject(userDTO.getEmail())
                .claim("name", userDTO.getName())
                .claim("role", userDTO.getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 36000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static Boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token); // 서명 검증
        } catch (JwtException e) {
            return false;
        }
        return true;
    }


    private static Boolean isTokenExpired(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
