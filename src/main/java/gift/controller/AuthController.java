package gift.controller;

import gift.dto.LoginRequest;
import gift.dto.LoginResponse;
import gift.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class AuthController {
    private final AuthService loginService;

    public AuthController(AuthService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/token")
    public LoginResponse login(@RequestBody LoginRequest requestDto) {
        return loginService.login(requestDto);
    }

}
