package gift.controller;

import gift.dto.MemberRequestDTO;
import gift.dto.MemberResponseDTO;
import gift.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 (회원 추가)
    @PostMapping("/register")
    public ResponseEntity<MemberResponseDTO> register(@RequestBody MemberRequestDTO memberDTO) {
        MemberResponseDTO registeredMember = memberService.registerMember(memberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredMember);
    }

    // 로그인 (회원 검증)
    @PostMapping("/login")
    public ResponseEntity<MemberResponseDTO> login(@RequestBody MemberRequestDTO memberDTO) {
        MemberResponseDTO loggedInMember = memberService.loginMember(memberDTO);
        return ResponseEntity.ok(loggedInMember);
    }
}
