package gift.member.application;

import gift.member.dao.MemberDao;
import gift.member.domain.Member;
import gift.member.dto.MemberDto;
import gift.error.AuthenticationFailedException;
import gift.auth.security.JwtUtil;
import gift.member.util.MemberMapper;
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
