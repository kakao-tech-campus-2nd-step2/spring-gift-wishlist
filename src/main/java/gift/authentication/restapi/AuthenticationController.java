package gift.authentication.restapi;

import gift.authentication.restapi.dto.request.LoginRequest;
import gift.authentication.restapi.dto.response.LoginResponse;
import gift.core.domain.authentication.AuthenticationService;
import gift.core.domain.authentication.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/api/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Token token = authenticationService.authenticate(request.email(), request.password());
        return LoginResponse.of(token);
    }
}
