package gift.service;

import gift.dto.LoginRequestDto;
import gift.dto.LoginResponseDto;
import gift.exception.UserNotFoundException;
import gift.jwt.JwtUtil;
import gift.model.User;
import gift.repository.UserDao;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserDao userDao;

    public LoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    public LoginResponseDto login(LoginRequestDto requestDto) {
        User user = userDao.findByEmail(requestDto.getEmail());
        if (user == null || !user.getPassword().equals(requestDto.getPassword())) {
            throw new UserNotFoundException("해당 유저가 존재하지 않거나 비밀번호가 틀렸습니다.");
        }
        LoginResponseDto response = new LoginResponseDto(JwtUtil.createToken(user.getEmail()));
        return response;
    }
}
