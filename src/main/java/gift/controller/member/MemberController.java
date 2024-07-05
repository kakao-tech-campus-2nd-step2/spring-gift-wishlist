package gift.controller.member;

import gift.dto.Member;
import gift.dto.Token;
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
    public ResponseEntity registerMember(@Valid @RequestBody Member member) {
        if(memberService.checkEmailDuplicate(member.getEmail())){
            return ResponseEntity.badRequest().body("이미 사용중인 이메일");
        }
        Long registeredMemberId = memberService.registerMember(member);
        Token newToken = tokenService.generateToken(registeredMemberId);// 생성되면 디비에 저장이 되야됨

        return ResponseEntity.ok(new TokenResponse(newToken.getValue()));
    }

    @PostMapping("/members/login")
    public ResponseEntity<TokenResponse> loginMember(@Valid @RequestBody Member member) {
        Long registeredMemberId = memberService.loginMember(member);
        Token token = tokenService.getToken(registeredMemberId);
        if (token == null) {
            return ResponseEntity.ok(new TokenResponse());
        }
        return ResponseEntity.ok(new TokenResponse(token.getValue()));
    }

}
