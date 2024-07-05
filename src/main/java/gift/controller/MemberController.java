package gift.controller;

import gift.model.Member;
import gift.service.MemberService;
import gift.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Member member) {
        memberService.register(member.getEmail(), member.getPassword());
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Member member) {
        Member authenticatedMember = memberService.authenticate(member.getEmail(), member.getPassword());
        if (authenticatedMember != null) {
            String token = JwtUtil.generateToken(authenticatedMember);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("로그인 실패");
    }
}
