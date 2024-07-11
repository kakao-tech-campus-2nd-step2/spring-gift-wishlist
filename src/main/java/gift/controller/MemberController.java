package gift.controller;

import gift.dto.MemberDTO;
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
    public ResponseEntity<?> registerMember(@Valid @RequestBody MemberDTO memberDTO) {
        Member savedMember = memberService.register(memberDTO.getEmail(), memberDTO.getPassword());
        String token = jwtUtil.generateToken(savedMember.getEmail());
        return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody MemberDTO loginDetails) {
        String email = loginDetails.getEmail();
        String password = loginDetails.getPassword();
        String token = memberService.login(email, password);
        return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@LoginMember Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setPassword(member.getPassword());
        return ResponseEntity.ok(memberDTO);
    }
}
