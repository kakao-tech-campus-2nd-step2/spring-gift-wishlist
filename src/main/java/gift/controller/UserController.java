package gift.controller;

import gift.Dto.User;
import gift.ExceptionHandler.DuplicateValueException;
import gift.Jwt.JwtUtil;
import gift.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/login")
    public String loginRendering() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        if(loginService.login(user)) {
            return "success";
        }
        return "error";
    }

    @GetMapping("/user-info")
    public ResponseEntity<List<User>> userInfoRendering() {
        List<User> users = loginService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/signup")
    public String signupRendering() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") User user){
        if(loginService.saveUser(user)){
            return "login";
        }
        return "error";
    }

    @PostMapping("/members/register")
    public ResponseEntity<String> register(@ModelAttribute("user") User user){
        if(loginService.saveUser(user)){
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Authorization", "Bearer " + token)
                    .body("회원가입 성공 및 토큰 발급");
        }
        throw new DuplicateValueException("회원가입 실패.");
    }

    @PostMapping("/members/login")
    public ResponseEntity<String> authLogin(@RequestParam("email") String email,
                                            @RequestParam("password") String password,
                                            @RequestParam("type") String type,
                                            @RequestParam("token") String token){
        User user = new User(email, password, type);

        if(loginService.login(user)){
            if (!jwtUtil.ValidToken(token, user)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 유효하지 않습니다.");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패");

    }

}
