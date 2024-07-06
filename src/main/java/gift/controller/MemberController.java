package gift.controller;

import gift.dto.AuthResponse;
import gift.dto.LoginRequest;
import gift.dto.RegisterRequest;
import gift.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
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

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> memberRegister(@Valid @RequestBody RegisterRequest registerRequest) {
        var auth = memberService.register(registerRequest);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> memberLogin(@Valid @RequestBody LoginRequest loginRequest) {
        var auth = memberService.login(loginRequest);
        return ResponseEntity.ok(auth);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMember(@RequestAttribute("memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
