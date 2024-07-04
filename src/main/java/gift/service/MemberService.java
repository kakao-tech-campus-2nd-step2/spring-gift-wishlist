package gift.service;

import gift.common.exception.DuplicateEmailException;
import gift.controller.dto.request.SignUpRequest;
import gift.model.MemberDao;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void signUp(SignUpRequest request) {
        try {
            memberDao.save(request);
        } catch (DuplicateKeyException e) {
            throw new DuplicateEmailException("Email already exists");
        }
    }
}
