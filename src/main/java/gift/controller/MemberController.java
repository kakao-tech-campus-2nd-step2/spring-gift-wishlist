package gift.controller;

import gift.dto.MemberRequestDTO;
import gift.dto.MemberResponseDTO;
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

    @PostMapping("/register")
    public ResponseEntity<MemberResponseDTO> register(@RequestBody MemberRequestDTO memberDTO) {
        MemberResponseDTO registeredMember = memberService.registerMember(memberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredMember);
    }
}
