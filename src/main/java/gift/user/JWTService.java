package gift.user;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Service
public class JWTService {

    private String secretKey = "";

    public String generateAccessToken(UserDTO userDTO){
        Date now = new Date();
        String encodeString = userDTO.getEmail() + ":" + userDTO.getPassword();
        secretKey = Base64.getEncoder().encodeToString(encodeString.getBytes(StandardCharsets.UTF_8));
        System.out.println(secretKey);
        return Jwts.builder()
                .subject(userDTO.getNickname())
                .claim("email", userDTO.email)
                .issuedAt(now).expiration(createExpiredDate(now))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
        }


    private Date createExpiredDate(Date now) {
        return new Date(now.getTime() + (60 * (60 * 1000)));
    }

    public Jws<Claims> getClaims(String jwt) {
        try{
            return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8))).build().parseSignedClaims(jwt);
        }
        catch (SignatureException e){
            return null;
        }
    }

    public boolean tokenValidCheck(String jwt) {
        try{
            Jws<Claims> claims = getClaims(jwt);
            return true;
        }
        catch (ExpiredJwtException e){
            throw new JwtException("토큰이 만료되었습니다");
        }
        catch (JwtException e){
            throw new JwtException(e.getMessage());
        }
        catch (NullPointerException e){
            throw new JwtException("토큰이 비어있습니다");
        }
        catch (Exception e){
            throw new JwtException(e.getMessage());
        }
    }

}
