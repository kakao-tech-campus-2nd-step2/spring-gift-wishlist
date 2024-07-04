package gift.member.service;

import gift.member.domain.LoginResponseDTO;
import gift.member.domain.Member;
import gift.member.repository.MemberRepository;
import gift.member.util.JwtUtil;
import gift.member.util.AuthenticationFailedException;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private MemberRepository memberRepository;
    private JwtUtil jwtUtil;

    public MemberService(MemberRepository memberRepository, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDTO authenticate(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        if (member != null && password.equals(member.getPassword())) {
            LoginResponseDTO response = new LoginResponseDTO(jwtUtil.generateToken(member.getEmail()));
            return response;
        } else {
            throw new AuthenticationFailedException("Authentication failed");
        }
    }
}
