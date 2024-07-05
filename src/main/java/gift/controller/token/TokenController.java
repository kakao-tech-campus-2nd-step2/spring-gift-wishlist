package gift.controller.token;

import gift.dto.Member;
import gift.dto.Token;
import gift.dto.TokenResponse;
import gift.repository.TokenRepository;
import gift.repository.MemberRepository;
import gift.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    private final TokenService tokenService;
    private final TokenRepository tokenRepository;
    private final MemberRepository memberRepository;

    public TokenController(TokenService tokenService, TokenRepository tokenRepository, MemberRepository memberRepository) {
        this.tokenService = tokenService;
        this.tokenRepository = tokenRepository;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/auth/token")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody Member member){
        Token newToken = tokenService.generateToken(member.getId());
        return ResponseEntity.ok(new TokenResponse(newToken.getValue()));
    }
//    @PostMapping("token")
//    public ResponseEntity<String> validateAccessToken(@RequestBody Token token){
//
//    }

    //토큰 확인, 토큰 만료시키기
//    @Scheduled(cron = "0 0 0 1 * *")//매달 1일 토큰 만료 정책
//    public void expireAllTokensMonthly(){
//    //tokenReposiory.expireAll();
//    }

}
