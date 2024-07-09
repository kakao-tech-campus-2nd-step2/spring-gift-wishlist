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
        Optional<Member> existingMember = memberRepository.findByEmail(member.getEmail());
        if (existingMember.isPresent()) {
            throw new RuntimeException("Email is already registered");
        }

        String encodedPassword = Base64.getEncoder().encodeToString(member.getPassword().getBytes());
        member.setPassword(encodedPassword);
        return memberRepository.save(member);
    }

    public String login(String email, String password) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
            if (encodedPassword.equals(member.get().getPassword())) {
                return jwtUtil.generateToken(member.get().getEmail());
            }
        }
        return null;
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
