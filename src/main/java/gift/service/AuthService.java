package gift.service;

import gift.model.User;
import gift.model.dto.UserRequestDto;
import gift.repository.UserDao;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class AuthService {

    private int accessTokenExpMinutes = 5;

    private final UserDao userDao;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String generateToken(User user){
        Key key = Keys.hmacShaKeyFor("kjdafhlaksdjfalhskdjfalhssidajlasdifiqewuy2314jhfasdkfjh".getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
            .signWith(key)
            .expiration(new Date((new Date()).getTime() + accessTokenExpMinutes))
            .subject(user.getEmail())
            .issuedAt(new Date())
            .compact();
    }
}
