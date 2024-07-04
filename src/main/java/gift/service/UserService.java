package gift.service;

import gift.entity.User;
import gift.entity.UserDao;
import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;
    private final TokenService tokenService;

    public UserService(UserDao userDao, TokenService tokenService) {
        this.userDao = userDao;
        this.tokenService = tokenService;
    }

    public Long registerUser(String email, String password) {
        Optional<User> existingUser = userDao.selectUserByEmail(email);
        if (existingUser.isPresent()) {
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
        }

        User user = new User(null, email, password);
        return userDao.insertUser(user);
    }

    public String loginUser(String email, String password) {
        User user = userDao.selectUserByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED));

        if (!user.password.equals(password)) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED);
        }

        return tokenService.generateToken(user.email);
    }
}
