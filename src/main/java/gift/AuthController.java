package gift;

import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/signup")
    public void signUp(@Valid @RequestBody SignUpForm signUpForm) {
        authService.signUp(signUpForm);
    }

    @PostMapping("/login")
    public Map<String, String> login(@Valid @RequestBody LoginForm loginForm) {
        return authService.login(loginForm);
    }
}
