package gift.controller;
import gift.model.User;
import gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "login_form";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register_form";
    }

    // 로그인
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@ModelAttribute User user) {
        Optional<String> token = userService.login(user.getEmail(), user.getPassword());
        if (token.isPresent()) {
            return ResponseEntity.ok(Map.of("accessToken", token.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이메일 혹은 패스워드가 틀렸습니다.");
        }
    }

    // 회원가입
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> register(@ModelAttribute User user) {
        boolean registered = userService.register(user);
        if (registered) {
            return ResponseEntity.ok("회원가입이 정상적으로 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패");
        }
    }
}
