package gift.service;

import gift.jwt.JwtUtil;
import gift.model.member.Member;
import gift.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final JwtUtil jwtUtil;

    private PasswordEncoder passwordEncoder;


    public MemberService(MemberRepository memberRepository, JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
        this.memberRepository = memberRepository;
    }

    public String registerNewMember(Member member){
        memberRepository.save(member);
        return jwtUtil.generateToken(member);
    }

    public String loginMember(Member member){
        Member registeredMember = memberRepository.findByEmail(member.email());
        if (registeredMember != null && passwordEncoder.matches(member.password(), registeredMember.password())) {
            return jwtUtil.generateToken(member);
        }
        throw new RuntimeException("Invalid credentials");
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
