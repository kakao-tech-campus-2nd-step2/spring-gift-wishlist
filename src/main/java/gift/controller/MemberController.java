package gift.controller;

import gift.dto.LoginRequest;
import gift.dto.RegisterRequest;
import gift.dto.AuthResponse;
import gift.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> memberRegister(@Valid @RequestBody RegisterRequest registerRequest) {
        var auth = service.register(registerRequest);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> memberLogin(@Valid @RequestBody LoginRequest loginRequest) {
        var auth = service.login(loginRequest);
        return ResponseEntity.ok(auth);
    }
}
