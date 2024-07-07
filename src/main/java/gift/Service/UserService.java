package gift.Service;

import gift.DTO.JwtToken;
import gift.DTO.UserDto;
import gift.Exception.ForbiddenException;
import gift.Exception.UnauthorizedException;
import gift.Repository.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserDao userDao;
  private final JwtService jwtService;

  public UserService(UserDao userDao, JwtService jwtService) {
    this.userDao = userDao;
    this.jwtService = jwtService;
  }

  public UserDto userSignUp(UserDto userInfo) {
    userDao.createUser(userInfo);
    return userInfo;
  }

  public JwtToken userLogin(UserDto userInfo) {
    String email = userInfo.getEmail();
    String password = userInfo.getPassword();
    UserDto userByEmail = userDao.getUserByEmail(email);

    if (userByEmail == null){
      return null;
    }
    if (email.equals(userByEmail.getEmail()) && password.equals(
      userByEmail.getPassword())) {
      JwtToken jwtToken = jwtService.createAccessToken(userByEmail);
      if (jwtService.isValidToken(jwtToken)){
        throw new UnauthorizedException("토큰이 유효하지 않습니다.");
      }
      return jwtToken;
    }
    throw new ForbiddenException("아이디 비밀번호가 틀립니다.");
  }
}
