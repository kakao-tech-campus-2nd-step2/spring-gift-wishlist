package gift.controller;

import gift.model.Member;
import gift.service.MemberService;
import gift.util.JwtUtil;
import gift.util.LoginMember;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            Member savedMember = memberService.register(member);
            String token = jwtUtil.generateToken(savedMember.getEmail());
            return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Email is already registered")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member loginDetails) {
        try {
            String email = loginDetails.getEmail();
            String password = loginDetails.getPassword();
            String token = memberService.login(email, password);

            if (token != null) {
                return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid email or password");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@LoginMember Member member) {
        return ResponseEntity.ok(member);
    }
}
