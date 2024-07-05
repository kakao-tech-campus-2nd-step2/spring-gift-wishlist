package gift.controller;

import gift.dto.LoginRequest;
import gift.dto.RegisterRequest;
import gift.dto.AuthResponse;
import gift.service.MemberService;
import gift.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;

    public MemberController(MemberService memberService, AuthService authService) {
        this.memberService = memberService;
        this.authService = authService;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader) {
        memberService.deleteMember(id, authorizationHeader);
        return ResponseEntity.noContent().build();
    }
}
