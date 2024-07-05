package gift.domain.user.controller;

import gift.auth.jwt.Token;
import gift.domain.user.dto.UserDto;
import gift.domain.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Token> create(@RequestBody @Valid UserDto userDto) {
        Token token = userService.signUp(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
}
