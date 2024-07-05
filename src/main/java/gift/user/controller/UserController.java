package gift.user.controller;

import gift.user.dto.TokenDto;
import gift.user.dto.UserDto;
import gift.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 로그인 또는 회원가입을 통해 이메일과 비밀번호를 받아서 토큰을 반환해주는 컨트롤러
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입에 대한 핸들러
    @PostMapping("/registration")
    public TokenDto register(@ModelAttribute @Valid UserDto userDto) {
        // service로부터 비즈니스 로직을 완수한 결과를 반환
        return userService.register(userDto);
    }

    // 로그인에 대한 핸들러
    @PostMapping("/login")
    public TokenDto login(@ModelAttribute @Valid UserDto userDto) {
        // service로부터 비즈니스 로직을 완수한 결과를 반환
        return userService.login(userDto);
    }
}
