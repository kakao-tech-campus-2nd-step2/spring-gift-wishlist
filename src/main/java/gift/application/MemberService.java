package gift.application;

import gift.dao.MemberDao;
import gift.domain.Member;
import gift.dto.MemberDto;
import gift.error.AuthenticationFailedException;
import gift.util.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MemberService {

    private final MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void registerUser(MemberDto user) {
        memberDao.save(MemberMapper.toEntity(user));
    }

    public String login(MemberDto memberDto) {
        Member member = memberDao.findByEmail(memberDto.email())
                .orElseThrow(() -> new NoSuchElementException("해당 계정은 존재하지 않습니다."));

        if (!member.password()
                .equals(memberDto.password())) {
            throw new AuthenticationFailedException("인증에 실패하였습니다.");
        }

        return "accessToken";
    }

}
