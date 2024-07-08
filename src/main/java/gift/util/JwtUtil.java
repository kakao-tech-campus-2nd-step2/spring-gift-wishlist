package gift.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

import gift.dto.UserDto;

public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;
    
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; 

    public String generateToken(UserDto userDto) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userDto.getId());
        claims.put("name", userDto.getName());
        claims.put("email", userDto.getEmail());
        claims.put("role", userDto.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDto.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public String extractToken(String authorizationHeader){
        return authorizationHeader.substring(7);
    }

    public boolean validateToken(String authorizationHeader, UserDto userDto) {

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return false;
        }
        String token = authorizationHeader.substring(7);
        String email = extractAllClaims(token).getSubject();

        return (email.equals(userDto.getEmail()) && !isTokenExpired(token));
    }
}
