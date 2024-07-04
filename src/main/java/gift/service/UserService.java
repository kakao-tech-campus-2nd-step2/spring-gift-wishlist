package gift.service;

import gift.dto.UserRequestDto;
import gift.exception.UserAlreadyExistsException;
import gift.exception.UserNotFoundException;
import gift.model.User;
import gift.repository.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User makeUser(UserRequestDto requestDto) {
        User getUser = userDao.findByEmail(requestDto.getEmail());

        if (getUser == null) {
            User user = new User(
                    requestDto.getEmail(),
                    requestDto.getPassword()
            );
            userDao.insert(user);
            return user;
        }
        throw new UserAlreadyExistsException("해당 email의 계정이 이미 존재하는 계정입니다.");
    }

    public User getUser(String email) {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("해당 email의 계정이 존재하지 않습니다.");
        }
        return user;
    }
}
