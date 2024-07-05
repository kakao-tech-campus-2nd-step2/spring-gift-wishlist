package gift.controller;

import gift.domain.Member;
import gift.response.JwtResponse;
import gift.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Member memberDTO) {
        memberService.registerMember(memberDTO.getEmail(), memberDTO.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody Member memberDTO) {
        String jwt = memberService.login(memberDTO.getEmail(), memberDTO.getPassword());
        return ResponseEntity.ok()
            .body(new JwtResponse(jwt));
    }
}
