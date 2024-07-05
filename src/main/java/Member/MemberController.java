package Member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberDAO memberDAO;

    public MemberController() {
        this.memberDAO = memberDAO;
        memberDAO.create();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestHeader Member member) {
        try {
            var token = memberDAO.insert(member);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("유효하지 않은 이메일/비밀번호");
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestHeader Member member) {
        try {
            var token = memberDAO.getToken(member);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("유효하지 않은 이메일/비밀번호");
        }
    }

    public void validateToken(String token) throws HttpClientErrorException.Unauthorized{
        memberDAO.validateToken(token);
    }
}
