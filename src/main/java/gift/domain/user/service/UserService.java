package gift.domain.user.service;

import gift.auth.jwt.Token;
import gift.domain.user.dao.UserDao;
import gift.domain.user.dto.UserDto;
import gift.domain.user.dto.UserLoginDto;
import gift.domain.user.entity.Role;
import gift.domain.user.entity.User;
import gift.auth.jwt.JwtProvider;
import gift.exception.InvalidUserInfoException;
import java.util.Optional;
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
        Optional<User> user = userDao.findByEmail(userLoginDto.email());

        if (user.isEmpty()) {
            throw new InvalidUserInfoException("error.invalid.userinfo.email");
        }

        if (!user.get().getPassword().equals(userLoginDto.password())) {
            throw new InvalidUserInfoException("error.invalid.userinfo.password");
        }

        return jwtProvider.generateToken(user.get());
    }

    public Role verifyRole(Token token) {
        return jwtProvider.getAuthentication(token.token());
    }
}
