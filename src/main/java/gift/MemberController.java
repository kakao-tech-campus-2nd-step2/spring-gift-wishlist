package gift;

import org.springframework.beans.factory.annotation.Autowired;
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
        memberDao.insertMember(member);
        String token = jwtTokenUtil.generateToken(member.getEmail());
        return ResponseEntity.ok(new TokenResponseDto(token));
    }
}
