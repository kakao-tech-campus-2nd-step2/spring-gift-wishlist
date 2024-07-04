package gift.service;

import gift.dao.UserDao;
import gift.domain.User;
import gift.util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;
    private final JwtUtil jwtUtil;

    public UserService(UserDao userDao, JwtUtil jwtUtil) {
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
    }

    public void generateUser(User user) {
        userDao.signIn(user);
    }

    public String authenticateUser(User user) {
        User authenticatedIser = userDao.signIn(user);
        String accessToken = jwtUtil.generateToken(user.getEmail());

        return accessToken;
    }


}
