package gift.controller;

import gift.model.Login.JwtToken;
import gift.model.Login.LoginRequest;
import gift.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    private final JwtService jwtService;
    public LoginController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @RequestMapping(value = "/login/token", method = RequestMethod.POST)
    public ResponseEntity<JwtToken> createAuthenticationToken(@RequestBody LoginRequest loginRequest) {
        final String token = jwtService.generateToken(loginRequest.getEmail());
        JwtToken jwtToken = new JwtToken("Bearer", token);
        return ResponseEntity
                        .status(200)
                        .body(jwtToken);
    }
}
