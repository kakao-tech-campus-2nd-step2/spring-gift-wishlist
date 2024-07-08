package gift;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final JwtService jwtService;

    public MemberController(MemberService memberService, JwtService jwtService) {
        this.memberService = memberService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody Member member) {
        Member savedMember = memberService.createMember(member);
        Map<String, String> response = new HashMap<>();
        response.put("token", jwtService.generateToken(savedMember));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody Member member) {
        Member foundMember = memberService.getMemberByEmail(member.getEmail());
        if (foundMember != null && foundMember.getPassword().equals(member.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("token", jwtService.generateToken(foundMember));
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(403).build();
        }
    }
}
