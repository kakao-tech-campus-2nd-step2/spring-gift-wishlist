package gift.api.member;


import gift.global.utils.JwtUtil;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Optional<Member> getLoginMember(String token) {
        return memberDao.getMemberByEmail(JwtUtil.getEmailFromToken(token));
    }
}
