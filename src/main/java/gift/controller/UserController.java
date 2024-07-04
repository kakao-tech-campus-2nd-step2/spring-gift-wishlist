package gift.controller;

import gift.dto.CommonResponse;
import gift.dto.UserRequestDto;
import gift.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<CommonResponse> registerUser(@RequestBody UserRequestDto request){
        userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponse("유저가 성공적으로 생성되었습니다."));
    }
}
