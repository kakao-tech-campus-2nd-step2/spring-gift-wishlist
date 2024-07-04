package gift.controller;

import gift.DTO.Member;
import gift.constants.ErrorMessage;
import gift.constants.SuccessMessage;
import gift.jwt.JwtUtil;
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
    private final JwtUtil jwtUtil;

    public MemberController(MemberDao memberDao, JwtUtil jwtUtil) {
        this.memberDao = memberDao;
        this.jwtUtil = jwtUtil;
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

    /**
     * 로그인 기능.
     *
     * @return 성공 시, 200 OK 응답과 jwt 토큰을 함께 반환
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Member member) {
        Member queriedMember = memberDao.findByEmail(member.getEmail())
            .orElseThrow(() -> new NoSuchElementException(ErrorMessage.MEMBER_NOT_EXISTS_MSG));
        if (!queriedMember.getPassword().equals(member.getPassword())) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PASSWORD_MSG);
        }
        String token = jwtUtil.createJwt(member.getEmail(), 100 * 60 * 5);

        return ResponseEntity.ok().header("token", token)
            .body(SuccessMessage.LOGIN_MEMBER_SUCCESS_MSG);
    }
}
