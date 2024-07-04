package member.service;

import member.domain.Member;
import member.jwt.JwtUtil;
import member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private MemberRepository memberRepository;
    private JwtUtil jwtUtil;

    public MemberService(MemberRepository memberRepository, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        if (member != null && password.equals(member.getPassword())) {
            return jwtUtil.generateToken(member.getEmail());
        } else {
            throw new RuntimeException("Authentication failed");
        }
    }
}
