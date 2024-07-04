package gift.controller;

import gift.model.dto.TokenResponseDto;
import gift.model.dto.UserRequestDto;
import gift.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/token")
    public ResponseEntity<TokenResponseDto> login(
        @Valid @RequestBody UserRequestDto userRequestDto) {
        TokenResponseDto tokenResponseDto = new TokenResponseDto(authService.getToken(userRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(tokenResponseDto);
    }
}
