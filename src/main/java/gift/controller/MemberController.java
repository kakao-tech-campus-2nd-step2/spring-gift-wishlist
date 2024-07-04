package gift.controller;

import gift.dto.MemberRequestDTO;
import gift.dto.MemberResponseDTO;
import gift.exception.EmailAlreadyUsedException;
import gift.exception.ForbiddenException;
import gift.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        try {
            MemberResponseDTO registeredMember = memberService.registerMember(memberDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredMember);
        } catch (EmailAlreadyUsedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    // 로그인 (회원 검증)
    @PostMapping("/login")
    public ResponseEntity<MemberResponseDTO> login(@RequestBody MemberRequestDTO memberDTO) {
        try {
            MemberResponseDTO loggedInMember = memberService.loginMember(memberDTO);
            return ResponseEntity.ok(loggedInMember);
        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}
