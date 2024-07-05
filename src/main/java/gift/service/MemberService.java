package gift.service;

import gift.dao.MemberDao;
import gift.model.member.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberDao memberDao;
    public MemberService(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public void registerNewMember(Member member){
        memberDao.insertMemeber(member);
    }
}
