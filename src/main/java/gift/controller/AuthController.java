package gift.controller;

import gift.dto.UserDto;
import gift.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Savepoint;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping("")
    public String main() {
        return "auth/index";
    }
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "auth/sign-up";
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@ModelAttribute UserDto userDto) {
        ResponseEntity<Long> saveResult = authService.save(userDto);

        System.out.println(saveResult.getStatusCode());
        if (saveResult.getStatusCode() == HttpStatus.CREATED) {
            System.out.println("회원가입 성공");
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        } else {
            System.out.println("회원가입 실패");
            return ResponseEntity.status(saveResult.getStatusCode()).body("회원가입이 실패하였습니다.");
        }
    }



}
