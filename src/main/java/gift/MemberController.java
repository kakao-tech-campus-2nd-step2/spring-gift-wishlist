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
}
