package gift.application;

import gift.dao.MemberDao;
import gift.domain.Member;
import gift.dto.MemberDto;
import gift.error.AuthenticationFailedException;
import gift.security.JwtUtil;
import gift.util.MemberMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberDao memberDao;
    private final JwtUtil jwtUtil;

    public MemberService(
            MemberDao memberDao,
            JwtUtil jwtUtil
    ) {
        this.memberDao = memberDao;
        this.jwtUtil = jwtUtil;
    }

    public void registerUser(MemberDto user) {
        memberDao.save(MemberMapper.toEntity(user));
    }

    public String authenticate(MemberDto memberDto) {
        Member member = memberDao.findByEmail(memberDto.email())
                .orElseThrow(() -> new AuthenticationFailedException("해당 계정은 존재하지 않습니다."));

        if (!member.password()
                .equals(memberDto.password())) {
            throw new AuthenticationFailedException("비밀번호가 틀렸습니다.");
        }
        
        return jwtUtil.generateToken(member.id());
    }

}
