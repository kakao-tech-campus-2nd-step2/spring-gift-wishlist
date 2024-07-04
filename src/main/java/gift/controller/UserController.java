package gift.controller;

import gift.domain.model.JoinRequestDto;
import gift.domain.model.JoinResponseDto;
import gift.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<JoinResponseDto> joinUser(@RequestBody JoinRequestDto joinRequestDto) {
        String token = userService.joinUser(joinRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new JoinResponseDto(token));
    }
}
