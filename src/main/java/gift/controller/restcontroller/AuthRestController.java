package gift.controller.restcontroller;

import gift.controller.dto.request.SignInRequest;
import gift.controller.dto.response.TokenResponse;
import gift.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
public class AuthRestController {
    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody SignInRequest request) {
        TokenResponse response = TokenResponse.from(authService.signIn(request));
        return ResponseEntity.ok().body(response);
    }
}
