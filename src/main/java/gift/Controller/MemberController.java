package gift.Controller;

import gift.model.Member;
import gift.model.MemberDao;
import org.springframework.http.HttpStatus;
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
        System.out.println("signin: " + member.getId() + " " + member.getEmail());
        String token = JwtUtil.createJwt(member.getId(), member.getEmail());
        return token;
    }

    @PostMapping("/login")
    public String login(@RequestBody Member member){
        if (MemberDao.selectMember(member.getEmail()) == null) {
            throw new IllegalArgumentException("이메일을 확인해주세요.");
        }
        Member loginMember = MemberDao.selectMember(member.getEmail());

        if(Objects.equals(member.getPassword(), loginMember.getPassword())){
            System.out.println("login: " + loginMember.getId() + " " + member.getEmail());
            String token = JwtUtil.createJwt(loginMember.getId(), member.getEmail());
            return token;
        }
        else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }
}
