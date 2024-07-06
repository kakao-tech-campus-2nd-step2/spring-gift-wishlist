package gift.controller;

import gift.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members/register")
    public ResponseEntity<TokenResponse> register(@RequestBody MemberRequest memberRequest) {
        String token = memberService.register(memberRequest);
        TokenResponse response = new TokenResponse(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/members/login")
    public ResponseEntity<TokenResponse> login(@RequestBody MemberRequest memberRequest) {
        String token = memberService.authenticate(memberRequest);
        TokenResponse response = new TokenResponse(token);
        return ResponseEntity.ok(response);
    }

    private static class TokenResponse {
        private String token;

        public TokenResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
