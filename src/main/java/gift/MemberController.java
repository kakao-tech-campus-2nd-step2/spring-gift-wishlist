package gift;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberDao memberDao;
    private final JwtTokenUtil jwtTokenUtil;

    public MemberController(MemberDao memberDao, JwtTokenUtil jwtTokenUtil) {
        this.memberDao = memberDao;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@RequestBody Member member) {
        Optional<Member> existMember = memberDao.findMember(member);
        if (!existMember.isPresent()) {
            memberDao.insertMember(member);
            String token = jwtTokenUtil.generateToken(member.getEmail());
            return ResponseEntity.ok(new TokenResponseDto(token));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 회원정보가 존재합니다");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member member) {
        Optional<Member> existMember = memberDao.findMember(member);
        if (existMember.isPresent()) {
            String token = jwtTokenUtil.generateToken(member.getEmail());
            return ResponseEntity.ok(new TokenResponseDto(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("회원정보가 존재하지 않습니다");
        }
    }
}
