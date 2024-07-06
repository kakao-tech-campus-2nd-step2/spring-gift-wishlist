package gift.controller;

import gift.model.Member;
import gift.service.JwtUtil;
import gift.service.MemberService;
import gift.service.MemberService.MemberServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
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

        // 토큰 생성
        String token = jwtUtil.generateToken(member.getEmail());

        // 토큰을 JSON 응답 바디에 포함하여 반환
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", token);

        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Member member, @RequestHeader(value = "Authorization", required = false) String token) {
        // 이미 유효한 토큰이 있는지 확인
        if (token != null && jwtUtil.validateToken(token, member.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap("error", "Already logged in with a valid token"));
        }

        // 회원 인증
        MemberServiceStatus status = memberService.authenticateToken(member);

        // 인증 성공 시 처리
        if (status == MemberServiceStatus.SUCCESS) {
            // 토큰 생성 및 응답
            String generatedToken = jwtUtil.generateToken(member.getEmail());
            Map<String, String> responseBody = Collections.singletonMap("token", generatedToken);
            return ResponseEntity.ok().body(responseBody);
        }

        // 인증 실패 시 처리
        HttpStatus httpStatus = (status == MemberServiceStatus.NOT_FOUND) ? HttpStatus.UNAUTHORIZED : HttpStatus.FORBIDDEN;
        Map<String, String> errorBody = Collections.singletonMap("error", "Unauthorized");
        return ResponseEntity.status(httpStatus).body(errorBody);
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