package gift.controller;

import gift.DTO.Member;
import gift.constants.ErrorMessage;
import gift.constants.SuccessMessage;
import gift.repository.MemberDao;
import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/members")
public class MemberController {

    private final MemberDao memberDao;

    public MemberController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    /**
     * 회원가입 <br> 이미 존재하는 email이면 IllegalArgumentException
     *
     * @return ResponseEntity<String>
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerMember(@RequestBody Member member) {
        memberDao.findByEmail(member.getEmail())
            .ifPresent(user -> {
                throw new IllegalArgumentException();
            });
        memberDao.register(member);
        return ResponseEntity.ok().body(SuccessMessage.REGISTER_MEMBER_SUCCESS_MSG);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Member member) {
        Member queriedMember = memberDao.findByEmail(member.getEmail())
            .orElseThrow(() -> new NoSuchElementException(ErrorMessage.MEMBER_NOT_EXISTS_MSG));
        if (!queriedMember.getPassword().equals(member.getPassword())) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PASSWORD_MSG);
        }

        return ResponseEntity.ok(SuccessMessage.LOGIN_MEMBER_SUCCESS_MSG);
    }
}
