package gift;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final String secretKey;

    public MemberController(MemberService memberService, @Value("${jwt.secret}") String secretKey) {
        this.memberService = memberService;
        this.secretKey = secretKey;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody Member member) {
        Member savedMember = memberService.createMember(member);
        Map<String, String> response = new HashMap<>();
        response.put("token", generateToken(savedMember));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody Member member) {
        Member foundMember = memberService.getMemberByEmail(member.getEmail());
        if (foundMember != null && foundMember.getPassword().equals(member.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("token", generateToken(foundMember));
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    private String generateToken(Member member) {
        return Jwts.builder()
            .setSubject(member.getId().toString())
            .claim("email", member.getEmail())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
            .compact();
    }
}
