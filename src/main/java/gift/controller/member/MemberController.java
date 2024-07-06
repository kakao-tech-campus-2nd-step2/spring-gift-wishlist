package gift.controller.member;

import gift.dto.Token;
import gift.dto.request.MemberRequest;
import gift.dto.response.TokenResponse;
import gift.service.MemberService;
import gift.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    MemberService memberService;
    TokenService tokenService;

    public MemberController(MemberService memberService, TokenService tokenService) {
        this.memberService = memberService;
        this.tokenService = tokenService;
    }

    @PostMapping("/members/register")
    public ResponseEntity registerMember(@Valid @RequestBody MemberRequest member) {
        if (memberService.checkEmailDuplicate(member.getEmail())) {
            return ResponseEntity.badRequest().body("이미 사용중인 이메일");
        }
        Long registeredMemberId = memberService.registerMember(member);
        Token newToken = tokenService.generateToken(registeredMemberId);

        return ResponseEntity.ok(new TokenResponse(newToken.getValue()));
    }

    @PostMapping("/members/login")
    public ResponseEntity<TokenResponse> loginMember(@Valid @RequestBody MemberRequest member) {
        Long registeredMemberId = memberService.loginMember(member);
        Token token = tokenService.getToken(registeredMemberId);
        if (token == null) {
            return ResponseEntity.ok(new TokenResponse());
        }
        return ResponseEntity.ok(new TokenResponse(token.getValue()));
    }

}
