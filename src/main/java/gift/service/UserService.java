package gift.service;

import gift.jwt.JwtTokenProvider;
import gift.model.user.User;
import gift.repository.UserDao;
import gift.model.user.UserRequest;
import gift.model.user.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserDao userDao, JwtTokenProvider jwtTokenProvider) {
        this.userDao = userDao;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public UserResponse register(UserRequest userRequest) {
        User user = userDao.save(userRequest);
        return UserResponse.from(user);
    }

    public String login(UserRequest userRequest) {
        User user = userDao.findByEmail(userRequest.email());
        return jwtTokenProvider.createToken(user.getEmail());
    }
}
