package gift.controller;

import gift.model.Member;
import gift.model.MemberDTO;
import gift.security.JwtUtil;
import gift.service.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    public AuthController(MemberRepository memberRepository, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody MemberDTO memberDTO) {
        MemberDTO newMember = memberRepository.createMember(memberDTO);
        String token = jwtUtil.generateToken(newMember.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
}
