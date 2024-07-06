package gift.service;

import gift.exception.UserErrorCode;
import gift.exception.UserException;
import gift.model.User;
import gift.model.dto.UserRequestDto;
import gift.repository.UserDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
    private int accessTokenExpMinutes = 5;

    private final UserDao userDao;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getToken(UserRequestDto userRequestDto) throws UserException {
        User user = userDao.selectUserByEmail(userRequestDto.getEmail());
        if (!matchPassword(userRequestDto.getPassword(), user.getPassword())) {
            throw new UserException(UserErrorCode.FAILURE_LOGIN);
        }
        return generateToken(user);
    }

    private boolean matchPassword(String userRequestPassword, String userPassword) {
        return userRequestPassword.equals(userPassword);
    }

    private String generateToken(User user) {
        return Jwts.builder()
            .claim("name", user.getName())
            .claim("role", user.getRole())
            .subject(user.getId().toString())
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
    }

    public boolean validateAuthorization(String authorizationHeader) {
        if (authorizationHeader == null) {
            return false;
        }
        String type = extractType(authorizationHeader);
        String token = extractToken(authorizationHeader);

        return isBearer(type) && validateToken(token);
    }

    private boolean validateToken(String token) {
        try {
            Claims payload = getClaims(token);
            User user = userDao.selectUserById(Long.parseLong(payload.getSubject()));
            if (payload.get("name", String.class) == null || !user.getName()
                .equals(payload.get("name", String.class))) {
                return false;
            }
            if (payload.get("role", String.class) == null || !user.getRole()
                .equals(payload.get("role", String.class))) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        Jws<Claims> jws = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .build()
            .parseSignedClaims(token);
        return jws.getPayload();
    }

    private boolean isBearer(String type) {
        return type.equals("Bearer");
    }

    private String extractType(String authorizationHeader) {
        return authorizationHeader.split(" ")[0];
    }

    public String extractToken(String authorizationHeader) {
        return authorizationHeader.split(" ")[1];
    }
}
