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
        if (!matchUser(userRequestDto, user)) {
            throw new UserException(UserErrorCode.FAILURE_LOGIN);
        }
        return generateToken(user);
    }

    private boolean matchUser(UserRequestDto userRequestDto, User user) {
        return userRequestDto.getPassword().equals(user.getPassword());
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
        String[] authorizationContents = authorizationHeader.split(" ");
        String type = authorizationContents[0];
        String token = authorizationContents[1];

        return isBearer(type) && validateToken(token);
    }

    private boolean validateToken(String token) {
        try {
            Jws<Claims> jws = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseSignedClaims(token);
            Claims payload = jws.getPayload();
            User user = userDao.selectUserById(Long.parseLong(payload.getSubject()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isBearer(String type) {
        return type.equals("Bearer");
    }
}
