package gift.service;

import gift.domain.Member;
import gift.repository.MemberDao;
import gift.util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberDao memberDao;
    private final JwtUtil jwtUtil;

    public MemberService(MemberDao memberDao, JwtUtil jwtUtil) {
        this.memberDao = memberDao;
        this.jwtUtil = jwtUtil;
    }

    public String generateUser(Member member) {
        memberDao.signUp(member);
        return jwtUtil.generateToken(member.getEmail(), member.getPassword());
    }

    public String authenticateUser(Member member) {
        Member authenticatedMember = memberDao.signIn(member);
        return jwtUtil.generateToken(member.getEmail(), member.getPassword());
    }
}
