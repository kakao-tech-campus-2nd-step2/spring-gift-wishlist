package gift.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestHeader("Authorization") String str) {
        try {
            Member member = getMember(str);
            var token = memberService.signUp(member);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 이메일/비밀번호");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader("Authorization") String str) {
        try {
            Member member = getMember(str);
            var token = memberService.login(member);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 이메일/비밀번호");
        }
    }

    public Member getMember(String str) {
        String base64Credentials = str.substring("Basic ".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        String[] values = credentials.split(":", 2);
        return new Member(values[0], values[1]);
    }
}
