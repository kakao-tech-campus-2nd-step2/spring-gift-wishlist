package gift.service;

import gift.dao.MemberDao;
import gift.dto.LoginResultDto;
import gift.jwt.JwtUtil;
import gift.model.member.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberDao memberDao;

    private final JwtUtil jwtUtil;

    public MemberService(MemberDao memberDao, JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
        this.memberDao = memberDao;
    }

    public boolean registerNewMember(Member member) {
        if(memberDao.findByEmail(member.getEmail()) == null){
            memberDao.registerNewMember(member);
            return true;
        }
        return false;
    }

    public String returnToken(Member member){
        return jwtUtil.generateToken(member);
    }

    public LoginResultDto loginMember(Member member) {
        Member registeredMember = memberDao.findByEmail(member.getEmail());
        if (registeredMember != null &&member.getPassword().equals(registeredMember.getPassword())) {
            String token = jwtUtil.generateToken(member);
            return new LoginResultDto(token, true);
        }
        return new LoginResultDto(null, false);
    }

    public Member findByEmail(String email){
        return memberDao.findByEmail(email);
    }
}
