
package gift.service;

import gift.model.Member;
import gift.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public enum MemberServiceStatus {
        SUCCESS,
        EMAIL_ALREADY_EXISTS,
        NOT_FOUND,
        UNAUTHORIZED
    }

    public MemberServiceStatus authenticateToken(Member member) {
        Optional<Member> foundMemberOpt = memberRepository.findByEmail(member.getEmail());

        if (foundMemberOpt.isEmpty()) {
            return MemberServiceStatus.NOT_FOUND;
        }

        Member foundMember = foundMemberOpt.get();

        if (!member.getPassword().equals(foundMember.getPassword())) {
            return MemberServiceStatus.UNAUTHORIZED;
        }


        return MemberServiceStatus.SUCCESS;
    }

    public MemberServiceStatus save(Member member) {
        if (existsByEmail(member.getEmail())) {
            return MemberServiceStatus.EMAIL_ALREADY_EXISTS;
        }

        memberRepository.save(member);
        return MemberServiceStatus.SUCCESS;
    }

    public boolean existsByEmail(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }
}