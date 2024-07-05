package gift.controller;

import gift.dto.UserRequestDto;
import gift.dto.UserResponseDto;
import gift.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserRequestDto userRequest) {
        return new ResponseEntity<>(userService.registerUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody @Valid UserRequestDto userRequestDto) {
        // TODO: 로그인이 유효하면 token 반환
        return null;
    }

}
