package gift.controller;

import gift.model.Member;
import gift.service.MemberService;
import gift.util.JwtUtil;
import gift.util.LoginMember;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @Autowired
    public MemberController(MemberService memberService, JwtUtil jwtUtil) {
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@Valid @RequestBody Member member) {
        Member savedMember = memberService.register(member);
        String token = jwtUtil.generateToken(savedMember.getEmail());
        return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member loginDetails) {
        String email = loginDetails.getEmail();
        String password = loginDetails.getPassword();
        String token = memberService.login(email, password);

        if (token != null) {
            return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
        } else {
            return ResponseEntity.status(403).body("Invalid email or password");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@LoginMember Member member) {
        return ResponseEntity.ok(member);
    }
}
