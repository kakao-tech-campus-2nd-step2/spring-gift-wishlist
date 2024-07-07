package gift.controller;

import gift.dto.MemberDto;
import gift.dto.Token;
import gift.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Token> signIn(@RequestBody MemberDto member) {
        HttpHeaders headers = new HttpHeaders();
        Token token = authService.login(member);
        headers.add("Authorization", token.toString());
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(token);
    }
}
