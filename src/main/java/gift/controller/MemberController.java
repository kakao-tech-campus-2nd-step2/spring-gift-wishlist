package gift.controller;

import gift.model.Member;
import gift.service.MemberService;
import gift.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

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
        Member savedMember = memberService.registerMember(member);
        String token = jwtUtil.generateToken(savedMember.getEmail());
        return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member member) {
        return memberService.findByEmail(member.getEmail())
                .filter(m -> memberService.checkPassword(m, member.getPassword()))
                .map(m -> ResponseEntity.ok().body("{\"token\":\"" + jwtUtil.generateToken(m.getEmail()) + "\"}"))
                .orElseGet(() -> ResponseEntity.status(403).body("Invalid email or password"));
    }
}
