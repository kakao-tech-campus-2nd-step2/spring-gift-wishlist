package gift.service;

import gift.model.Member;
import gift.repository.MemberRepository;
import gift.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public MemberService(MemberRepository memberRepository, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    public Member register(Member member) {
        try {
            String encodedPassword = Base64.getEncoder().encodeToString(member.getPassword().getBytes());
            member.setPassword(encodedPassword);
            return memberRepository.save(member);
        } catch (Exception e) {
            throw new RuntimeException("Error registering member: " + e.getMessage(), e);
        }
    }

    public String login(String email, String password) {
        try {
            Optional<Member> member = memberRepository.findByEmail(email);
            if (member.isPresent()) {
                String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
                if (encodedPassword.equals(member.get().getPassword())) {
                    return jwtUtil.generateToken(member.get().getEmail());
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error during login: " + e.getMessage(), e);
        }
    }
}
