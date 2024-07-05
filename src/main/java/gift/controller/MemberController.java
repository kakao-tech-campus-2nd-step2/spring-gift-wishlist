package gift.controller;

import gift.dto.MemberRequestDto;
import gift.dto.TokenResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gift.service.MemberService;

@RestController
@RequestMapping("/api/users")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody MemberRequestDto request){
        String token = memberService.loginMember(request);
        return ResponseEntity.ok(new TokenResponseDto(token));
    }
}
