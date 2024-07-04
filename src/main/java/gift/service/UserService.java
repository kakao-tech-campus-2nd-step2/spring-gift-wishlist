package gift.service;

import gift.dao.UserDao;
import gift.dto.UserDTO;
import gift.entity.User;
import gift.exception.InternalServerExceptions.DuplicatedUserException;
import gift.exception.BadRequestExceptions.UserNotFoundException;
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
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void login(UserDTO userDto) throws RuntimeException {
        if(userDao.countUser(new User(userDto)) < 1)
            throw new UserNotFoundException("아이디 또는 비밀번호가 올바르지 않습니다.");

        if(userDao.countUser(new User(userDto)) > 1){
            throw new DuplicatedUserException(userDto.email() + "is Duplicated in DB");
        }
    }
}
