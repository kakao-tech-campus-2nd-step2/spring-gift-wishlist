package gift.service;

import gift.dao.MemberDAO;
import gift.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private final MemberDAO memberDAODAO;
    private final JwtService jwtService;

    @Autowired
    public MemberService(MemberDAO memberDAODAO, JwtService jwtService) {
        this.memberDAODAO = memberDAODAO;
        this.jwtService = jwtService;
    }


    public Member register(Member member) throws Exception {
        Member getMember = memberDAODAO.findByEmail(member.getEmail());

        if (getMember != null) {
            throw new Exception("해당 email의 계정이 이미 존재합니다.");
        }

        return memberDAODAO.save(member);

    }
}
