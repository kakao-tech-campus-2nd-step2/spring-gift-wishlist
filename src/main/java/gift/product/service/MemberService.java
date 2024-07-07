package gift.product.service;

import gift.product.dao.MemberDao;
import gift.product.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberDao memberDao;

    @Autowired
    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
        memberDao.createMemberTable();
    }

    public void signUp(Member member) {
        memberDao.signUp(member);
    }

    public boolean validateMember(Member member) {
        return memberDao.validateMember(member);
    }

    public boolean isExistsMember(String email) {
        return memberDao.isExistsMember(email);
    }
}
