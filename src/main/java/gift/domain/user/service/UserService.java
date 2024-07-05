package gift.domain.user.service;

import gift.auth.jwt.Token;
import gift.domain.user.dao.UserDao;
import gift.domain.user.dto.UserDto;
import gift.domain.user.dto.UserLoginDto;
import gift.domain.user.entity.Role;
import gift.domain.user.entity.User;
import gift.auth.jwt.JwtProvider;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;
    private final JwtProvider jwtProvider;

    public UserService(UserDao userDao, JwtProvider jwtProvider) {
        this.userDao = userDao;
        this.jwtProvider = jwtProvider;
    }

    public Token signUp(UserDto userDto) {
        User user = userDto.toUser();
        User savedUser = userDao.insert(user);
        
        return jwtProvider.generateToken(savedUser);
    }

    public Token login(UserLoginDto userLoginDto) {
        User user = userDao.findByEmail(userLoginDto.email())
            .orElseThrow(() -> new IllegalArgumentException(userLoginDto.email()));

        if (!user.getPassword().equals(userLoginDto.password())) {
            throw new IllegalArgumentException("error.invalid.userinfo.password");
        }

        return jwtProvider.generateToken(user);
    }

    public Role verifyRole(Token token) throws IllegalAccessException {
        return jwtProvider.getAuthentication(token.token());
    }
}
