package gift.service;

import gift.dao.ProductDao;
import gift.dao.UserDao;
import gift.dto.UserDTO;
import gift.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void signUp(UserDTO userDto){
        try{
            userDao.insertUser(new User(userDto));
        } catch (Exception e){

        }
    }
}
