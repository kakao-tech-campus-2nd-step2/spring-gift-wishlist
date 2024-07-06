package gift.controller;

import gift.Entity.User;
import gift.ExceptionHandler.DuplicateValueException;
import gift.Authorization.JwtUtil;
import gift.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //@ModelAttribute("user") User user
    public ResponseEntity<Map<String, String>> register(@ModelAttribute("user") User user) {
        if (loginService.saveUser(user)) {
            String token = jwtUtil.generateToken(user);
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("token", token);
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token)
                    .body(responseBody);
        }
        throw new DuplicateValueException("회원가입 실패.");
    }


    @PostMapping("/members/login")
    public ResponseEntity<String> authLogin(@RequestParam("email") String email,
                                            @RequestParam("password") String password,
                                            @RequestParam("type") String type) {

        User user = new User(email, password, type); // Assuming type is parsed to Integer
        if (loginService.login(user)) {
            String token = jwtUtil.generateToken(user);
            System.out.println("Generated token: " + token);
            return ResponseEntity.ok("로그인 성공");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패");
    }




}
