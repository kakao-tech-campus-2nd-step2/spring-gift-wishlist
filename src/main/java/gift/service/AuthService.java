package gift.service;

import gift.dto.LoginRequest;
import gift.dto.LoginResponse;
import gift.exception.UserNotFoundException;
import gift.jwt.JwtUtil;
import gift.model.User;
import gift.repository.UserDao;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserDao userDao;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public LoginResponse login(LoginRequest requestDto) {
        User user = userDao.findByEmail(requestDto.getEmail());
        if (user == null) {
            throw new UserNotFoundException("해당 유저가 존재하지 않습니다.");
        } else if (userMatchPassword(user, requestDto.getPassword())) {
            throw new UserNotFoundException("비밀번호가 일치하지 않습니다.");
        }
        LoginResponse response = new LoginResponse(JwtUtil.createToken(user.getEmail()));
        return response;
    }

    private boolean userMatchPassword(User user, String inputPassword) {
        if (user.getPassword().equals(inputPassword)) {
            return true;
        }
        return false;
    }
}
