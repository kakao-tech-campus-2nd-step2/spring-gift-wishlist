package gift.service;

import gift.common.exception.AuthenticationException;
import gift.model.Member;
import gift.model.MemberDao;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberDao  memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Member findMemberById(Long id) {
        return memberDao.findById(id)
                .orElseThrow(() ->
                        new AuthenticationException("Member with id " + id + " not found"));
    }
}
