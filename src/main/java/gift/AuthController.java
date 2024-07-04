package gift;

import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping("/token")
    @ResponseBody
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        String token = authService.authenticate(user);
        if (token != null) {
            return ResponseEntity.ok().body(Collections.singletonMap("accessToken", token));
        } else {
            return ResponseEntity.status(401).body(Collections.singletonMap("message", "잘못된 사용자 이름 또는 비밀번호입니다."));
        }
    }

    private static class TokenResponse {
        private String accessToken;

        public TokenResponse(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
