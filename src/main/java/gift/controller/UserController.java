package gift.controller;

import gift.model.dto.TokenResponseDto;
import gift.model.dto.UserRequestDto;
import gift.repository.UserDao;
import gift.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserDao userDao;
    private final AuthService authService;

    public UserController(UserDao userDao, AuthService authService) {
        this.userDao = userDao;
        this.authService = authService;
    }

    @PostMapping("/members/register")
    public ResponseEntity<TokenResponseDto> register(
        @Valid @RequestBody UserRequestDto userRequestDto) {
        userDao.insertUser(userRequestDto.toEntity());
        TokenResponseDto tokenResponseDto = new TokenResponseDto(
            authService.getToken(userRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(tokenResponseDto);
    }

    @PostMapping("/members/login")
    public ResponseEntity<TokenResponseDto> login(
        @Valid @RequestBody UserRequestDto userRequestDto) {
        TokenResponseDto tokenResponseDto = new TokenResponseDto(
            authService.getToken(userRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(tokenResponseDto);
    }
}
