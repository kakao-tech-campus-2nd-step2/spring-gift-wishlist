package gift.controller.token;

import gift.dto.Member;
import gift.dto.Token;
import gift.dto.response.TokenResponse;
import gift.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/auth/token")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody Member member) {
        Token newToken = tokenService.generateToken(member.getId());
        return ResponseEntity.ok(new TokenResponse(newToken.getValue()));
    }

}
