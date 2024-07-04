package gift;

import gift.auth.AuthService;
import gift.user.User;
import gift.user.UserService;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user.");
        }
    }

    @PostMapping("/login/token")
    @ResponseBody
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        String token = authService.authenticate(user);
        if (token != null) {
            return ResponseEntity.ok().body(Collections.singletonMap("accessToken", token));
        } else {
            return ResponseEntity.status(403).body(Collections.singletonMap("message", "잘못된 사용자 이름 또는 비밀번호입니다."));
        }
    }
}
