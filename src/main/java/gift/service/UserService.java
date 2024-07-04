package gift.service;

import gift.dao.UserDao;
import gift.domain.User;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao=userDao;
    }

    public void generateUser(User user){
        userDao.signIn(user);
    }

    public String authenticateUser(User user){
        return "token 반환해야함.";
    }


}
