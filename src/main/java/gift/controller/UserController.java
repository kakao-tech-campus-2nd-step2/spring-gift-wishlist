package gift.controller;

import gift.controller.dto.TokenResponseDto;
import gift.controller.dto.UserRequestDto;
import gift.repository.UserDao;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/login/token")
    public ResponseEntity<TokenResponseDto> login(@Valid @RequestBody UserRequestDto userRequestDto) {
        TokenResponseDto tokenResponseDto = new TokenResponseDto("example");
        return ResponseEntity.status(HttpStatus.OK).body(tokenResponseDto);
    }
}
