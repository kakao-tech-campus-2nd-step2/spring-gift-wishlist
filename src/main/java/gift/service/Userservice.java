package gift.service;

import gift.domain.User;
import gift.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Userservice {

    private final UserDao userDao;

    @Autowired
    public Userservice(UserDao userDao) {
        this.userDao = userDao;
    }

    public void generateUser(User user) {
        userDao.signUp(user);
    }

    public String authenticateUser(User user) {
        return "token";
    }

}
