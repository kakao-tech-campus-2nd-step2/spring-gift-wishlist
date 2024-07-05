package gift.Controller;

import gift.Exception.LoginException;
import gift.Exception.RegisterException;
import gift.Model.Role;
import gift.Model.User;
import gift.Model.UserInfo;
import gift.Model.UserInfoDAO;
import gift.Token.JwtToken;
import gift.Token.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserInfoDAO userInfoDAO;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginController(UserInfoDAO userInfoDAO, JwtTokenProvider jwtTokenProvider){
        this.userInfoDAO = userInfoDAO;
        this.jwtTokenProvider = jwtTokenProvider;
        userInfoDAO.insertUser(new UserInfo("admin", "1234", Role.ADMIN));
    }

    @PostMapping("/members/register")
    public JwtToken register(@RequestBody User user){
        int count = userInfoDAO.countUser(user.email());
        if (count == 0) {
            UserInfo userInfo = new UserInfo(user.email(), user.password(), Role.CONSUMER);
            userInfoDAO.insertUser(userInfo);
            return new JwtToken(jwtTokenProvider.createToken(userInfo), "bearer");
        }

        throw new RegisterException();
    }

    @PostMapping("/members/login")
    public JwtToken login(@RequestBody User user){
        UserInfo userInfo1 = userInfoDAO.selectUser(user.email());
        if(userInfo1.password().equals(user.password())){
            return new JwtToken(jwtTokenProvider.createToken(userInfo1), "bearer");
        }

        throw new LoginException();
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<?> handleLoginException(LoginException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RegisterException.class)
    public ResponseEntity<?> handleRegisterException(RegisterException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
