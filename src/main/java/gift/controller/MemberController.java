package gift.controller;

import gift.dto.MemberDto;
import gift.entity.Member;
import gift.service.MemberService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register") // 생성된 회원 정보로 JWT 토큰을 생성 -> 응답으로 반환
    public ResponseEntity<Map<String, String>> register(@RequestBody MemberDto memberDto) {
        // 회원 가입 처리
        Member member = memberService.register(memberDto.getEmail(), memberDto.getPassword());
        // 입력받은 정보로 부터 토큰 생성
        String token = generateToken(member);
        // 응답으로 사용자 정보 맵 반환
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login") // 인증 성공 여부에 따라 다른 결과 반환
    public ResponseEntity<Map<String, String>> login(@RequestBody MemberDto memberDto) {
        // 로그인 처리
        Member member = memberService.login(memberDto.getEmail(), memberDto.getPassword());
        // 로그인 성공 시 JWT 생성
        if (member == null) {
            return ResponseEntity.status(401).build(); // 로그인 실패시 401 Unauthorized 반환
        }
        String token = generateToken(member);
        // 응답으로 토큰을 포함한 맵 반환
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    // 멤버 객체를 처리해서 토큰을 생성
    private String generateToken(Member member) {
        return Jwts.builder()
                .setSubject(member.getId().toString())
                .claim("email", member.getEmail())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}