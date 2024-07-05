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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginViewController {
    private final UserInfoDAO userInfoDAO;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginViewController(UserInfoDAO userInfoDAO, JwtTokenProvider jwtTokenProvider){
        this.userInfoDAO = userInfoDAO;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User("a","1"));
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model){
        int count = userInfoDAO.countUser(user.email());
        if (count == 0) {
            UserInfo userInfo = new UserInfo(user.email(), user.password(), Role.CONSUMER);
            userInfoDAO.insertUser(userInfo);
            return "redirect:/login";
        }

        throw new RegisterException();
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User("a","1"));
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model){
        UserInfo userInfo1 = userInfoDAO.selectUser(user.email());
        if(userInfo1.password().equals(user.password())){
            return "products";
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
