package gift.Controller;

import gift.model.Member;
import gift.model.MemberDao;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/member")
public class MemberController {
    long id = 0L;

    private final MemberDao MemberDao;

    public MemberController(gift.model.MemberDao memberDao) {
        MemberDao = memberDao;
    }

    @PostMapping("/signin")
    public String signin(@RequestBody Member member){
        id++;
        member.setId(id);
        MemberDao.insertMember(member);
        String token = JwtUtil.createJwt(member.getEmail());
        return token;
    }

    @PostMapping("/login")
    public String login(@RequestBody Member member){
        if(Objects.equals(member.getPassword(), MemberDao.selectMember(member.getEmail()).getPassword())){
            String token = JwtUtil.createJwt(member.getEmail());
            return token;
        }
        return "NO";
    }
}
