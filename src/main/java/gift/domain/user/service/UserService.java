package gift.domain.user.service;

import gift.domain.user.dao.UserDao;
import gift.domain.user.dto.UserDto;
import gift.domain.user.entity.User;
import gift.domain.user.service.jwt.JwtProvider;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;
    private final JwtProvider jwtProvider;

    public UserService(UserDao userDao, JwtProvider jwtProvider) {
        this.userDao = userDao;
        this.jwtProvider = jwtProvider;
    }

    public String signUp(UserDto userDto) {
        User user = userDto.toUser();

        User savedUser = userDao.insert(user);
        return jwtProvider.generateToken(savedUser);
    }
}
