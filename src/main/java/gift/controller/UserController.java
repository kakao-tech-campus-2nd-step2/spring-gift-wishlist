package gift.controller;

import gift.domain.model.UserRequestDto;
import gift.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> joinUser(@RequestBody UserRequestDto userRequestDto) {
        String token = userService.joinUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
}
