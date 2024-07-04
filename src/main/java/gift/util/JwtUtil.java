package gift.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

@Component // 스프링 빈으로 등록
public class JwtUtil {

    //Token 식별자
    public static final String BEARER_PREFIX = "Bearer ";

    //토큰 만료시간
    private static final long TOKEN_TIME = 60*60*1000L ;// 60분

    @Value("${jwt.secret.key}")//appliocation.properties에 작성
    private String SECRET_KEY;

    //SecretKey 암호화.  2024년 4월기준으로 Jwts.builder().signWith() 사용법이 이렇게 바뀌었다고함.
    private SecretKey getSigningKey(){
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateToken(String email){
        return BEARER_PREFIX +
            Jwts.builder()
                .setSubject(email) //주체 설정
                .setIssuedAt(new Date(System.currentTimeMillis())) //발행 시간 설정
                .setExpiration(new Date(System.currentTimeMillis()+TOKEN_TIME)) // 만료 시간 설정
                .signWith(getSigningKey())
                .compact();
    }

   //Jwt Parsing, Claim추출
   public Claims extractClaims(String token){
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();
   }



}
