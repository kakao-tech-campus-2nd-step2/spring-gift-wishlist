package gift.service;

import gift.exception.UserErrorCode;
import gift.exception.UserException;
import gift.model.User;
import gift.model.dto.UserRequestDto;
import gift.repository.UserDao;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private int accessTokenExpMinutes = 5;

    private final UserDao userDao;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getToken(UserRequestDto userRequestDto) throws UserException {
        User user = userDao.selectUserByEmail(userRequestDto.getEmail());
        if (!matchUser(userRequestDto, user)) {
            throw new UserException(UserErrorCode.NOT_AUTHENTICATION);
        }
        return generateToken(user);
    }

    private boolean matchUser(UserRequestDto userRequestDto, User user) {
        return userRequestDto.getPassword().equals(user.getPassword());
    }

    private String generateToken(User user) {
        Key key = Keys.hmacShaKeyFor(
            "kjdafhlaksdjfalhskdjfalhssidajlasdifiqewuy2314jhfasdkfjh".getBytes(
                StandardCharsets.UTF_8));
        return Jwts.builder()
            .signWith(key)
            .expiration(new Date((new Date()).getTime() + accessTokenExpMinutes))
            .subject(user.getEmail())
            .issuedAt(new Date())
            .compact();
    }
}
