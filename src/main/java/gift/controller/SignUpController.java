package gift.controller;

import gift.authorization.JwtUtil;
import gift.entity.User;
import gift.service.SignUpService;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class SignUpController {

    private final SignUpService signUpService;
    private final JwtUtil jwtUtil;

    @Autowired
    public SignUpController(SignUpService signUpService, JwtUtil jwtUtil) {
        this.signUpService = signUpService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/login")
    public String loginRendering() {
        return "login";
    }

    @Description("임시 확인용 html form. service x ")
    @GetMapping("/user-info")
    public ResponseEntity<List<User>> userInfoRendering() {
        List<User> users = signUpService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/signup")
    public String signupRendering() {
        return "signup";
    }

    @PostMapping("/members")
    public ResponseEntity<Map<String, String>> register(@ModelAttribute("user") User user) {
        return signUpService.signUp(user);
    }

}
