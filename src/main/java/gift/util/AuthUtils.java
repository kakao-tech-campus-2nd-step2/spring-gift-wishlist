package gift.util;

import gift.model.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;

public abstract class AuthUtils {

    @Value("${SECRET_KEY}")
    private static String secretKey;

    private AuthUtils() {
    }

    public static String createAccessTokenWithMember(Member member) {
        var token = Jwts.builder()
                .subject(member.getId().toString())
                .claim("name", member.getName())
                .claim("role", member.getRole())
                .signWith(getSecretKey())
                .compact();
        return token;
    }

    public static Long getMemberIdWithToken(String token){
        var id = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return Long.parseLong(id);
    }

    private static SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }


}
