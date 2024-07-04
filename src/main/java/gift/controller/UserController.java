package gift.controller;

import gift.dto.UserRequestDto;
import gift.dto.UserResponseDto;
import gift.service.JwtUtil;
import gift.service.UserService;
import java.util.HashMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto requestDto){
        userService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDto("유저 생성 완료"));
    }

}
