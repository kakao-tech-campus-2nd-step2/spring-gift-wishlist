package gift.controller;

import gift.dto.LoginRequestDto;
import gift.dto.LoginResponseDto;
import gift.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/token")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto) {
        return loginService.login(requestDto);
    }

}
