package gift.controller;


import gift.model.Member;
import gift.service.MemberService;
import gift.util.JwtUtil;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        this.jwtUtil = new JwtUtil();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Member member) {
        if (memberService.existsByEmail(member.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        Member registeredMember = memberService.registerMember(member);
        String token = jwtUtil.generateToken(registeredMember.getId(), registeredMember.getName(),
            registeredMember.getRole());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member member) {
        Member authenticatedMember = memberService.authenticate(member.getEmail(), member.getPassword());
        if (authenticatedMember == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid email or password");
        }
        String token = jwtUtil.generateToken(authenticatedMember.getId(),
            authenticatedMember.getName(), authenticatedMember.getRole());
        return ResponseEntity.ok(Map.of("token", token));
    }
}