package gift.utils;

import gift.model.MemberRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public abstract class AuthUtils {
    public static Long getMemberIdWithToken(String token, String key) {
        var id = Jwts.parser()
                .verifyWith(getSecretKey(key))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return Long.parseLong(id);
    }

    public static MemberRole getMemberRoleWithToken(String token, String key) {
        var role = Jwts.parser()
                .verifyWith(getSecretKey(key))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role");
        return MemberRole.valueOf(role.toString());
    }

    public static SecretKey getSecretKey(String key) {
        return Keys.hmacShaKeyFor(key.getBytes());
    }
}
