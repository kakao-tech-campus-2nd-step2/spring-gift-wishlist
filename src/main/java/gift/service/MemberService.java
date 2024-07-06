package gift.service;

import gift.dto.LoginResultDto;
import gift.jwt.JwtUtil;
import gift.model.member.Member;
import gift.repository.MemberRepository;
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

    public LoginResultDto loginMember(Member member) {
        Member registeredMember = memberRepository.findByEmail(member.getEmail());
        if (registeredMember != null &&member.getPassword().equals(registeredMember.getPassword())) {
            String token = jwtUtil.generateToken(member);
            return new LoginResultDto(token, true);
        }
        return new LoginResultDto(null, false);
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
