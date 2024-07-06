package gift.service;

import gift.jwt.JwtUtil;
import gift.model.member.Member;
import gift.repository.MemberRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;


@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final JwtUtil jwtUtil;


    public MemberService(MemberRepository memberRepository, JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
        this.memberRepository = memberRepository;
    }

    public String registerNewMember(Member member){
        memberRepository.save(member);
        return jwtUtil.generateToken(member);
    }

    public String loginMember(Member member) throws AuthenticationException {
        Member registeredMember = memberRepository.findByEmail(member.email());
        if (registeredMember != null &&member.password().equals(registeredMember.password())) {
            return jwtUtil.generateToken(member);
        }
        throw new AuthenticationException("Invalid email or password");
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
