package gift.member.service;

import gift.common.util.JwtUtil;
import gift.member.dto.MemberRequest;
import gift.member.model.Member;
import gift.member.repository.MemberRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public MemberService(MemberRepository memberRepository, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    public String register(MemberRequest memberRequest) {
        Member existingMember = memberRepository.findByEmail(memberRequest.getEmail());
        if (existingMember != null) {
            throw new DuplicateKeyException("이미 존재하는 이메일 : " + memberRequest.getEmail());
        }

        Member member = new Member();
        member.setEmail(memberRequest.getEmail());
        member.setPassword(memberRequest.getPassword());
        memberRepository.save(member);
        return jwtUtil.generateToken(member.getEmail());
    }

    public String login(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        if (member != null && member.getPassword().equals(password)) {
            return jwtUtil.generateToken(member.getEmail());
        }
        return null;
    }

    public Member getMemberFromToken(String token) {
        try {
            String email = jwtUtil.extractEmail(token);
            return memberRepository.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token이 잘못되었습니다.");
        }
    }

}
