package gift.service;

import gift.domain.User;
import gift.repository.UserDao;
import gift.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Userservice {

    private final UserDao userDao;
    private final JwtUtil jwtUtil;

    public Userservice(UserDao userDao, JwtUtil jwtUtil) {
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
    }

    public void generateUser(User user) {
        userDao.signUp(user);
    }

    public String authenticateUser(User user) {
        return jwtUtil.generateToken(user.getEmail(), user.getPassword());
    }
}
