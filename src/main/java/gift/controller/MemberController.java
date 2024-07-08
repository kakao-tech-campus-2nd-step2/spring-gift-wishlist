package gift.controller;

import gift.model.Member;
import gift.service.JwtUtil;
import gift.service.MemberService;
import gift.service.MemberServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

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
    public ResponseEntity<?> registerUser(@RequestBody Member member) {
        MemberServiceStatus status = memberService.save(member);

        if (status == MemberServiceStatus.EMAIL_ALREADY_EXISTS) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("error", "Email already exists"));
        }

        // 회원 가입 성공 응답 반환
        return ResponseEntity.ok().body(Collections.singletonMap("message", "Success"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Member member) {
        // 회원 인증
        MemberServiceStatus status = memberService.authenticateToken(member);

        if (status == MemberServiceStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "User not found"));
        }

        if (status == MemberServiceStatus.UNAUTHORIZED) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid password"));
        }

        // 토큰 생성 및 응답
        String token = jwtUtil.generateToken(member.getEmail());
        Map<String, String> responseBody = Collections.singletonMap("token", token);
        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String token) {
        // 토큰에서 이메일 추출
        String email = jwtUtil.extractEmail(token);

        if (email != null && jwtUtil.validateToken(token, email)) {
            return ResponseEntity.ok().body(Collections.singletonMap("message", "Token is valid"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid or expired token"));
    }
}