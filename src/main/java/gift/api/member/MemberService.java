package gift.api.member;


import java.util.Base64;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Optional<Member> getLoginMember(String token) {
        String[] credentials = new String(Base64.getDecoder().decode(token)).split(":");
        return memberDao.getMemberByEmailAndPassword(credentials[0], credentials[1]);
    }
}
