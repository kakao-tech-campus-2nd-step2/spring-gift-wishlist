package gift.controller;

import gift.dto.UserRequestDTO;
import gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRequestDTO userRequest) {
        userService.register(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    // 사용자 로그인
    @PostMapping("/login/token")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserRequestDTO userRequest) {
        String token = userService.authenticate(userRequest);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Map<String, String> response = new HashMap<>();
        response.put("accessToken", token);
        return ResponseEntity.ok(response);
    }
}
