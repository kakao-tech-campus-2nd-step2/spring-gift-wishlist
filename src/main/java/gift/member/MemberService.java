package gift.member;

import gift.exception.FailedLoginException;
import gift.token.JwtProvider;
import org.springframework.stereotype.Component;

@Component
public class MemberService {

    public final MemberRepository memberRepository;
    public final JwtProvider jwtProvider;

    public MemberService(
        MemberRepository memberRepository,
        JwtProvider jwtProvider
    ) {
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
    }

    public String register(Member member) {
        if (memberRepository.existMemberByEmail(member.email())) {
            throw new IllegalArgumentException("User already exists");
        }

        memberRepository.addMember(member);
        return jwtProvider.generateToken(member);
    }

    public String login(Member member) {
        if (!memberRepository.existMemberByEmail(member.email())) {
            throw new FailedLoginException("User does not exist");
        }
        member.isAuthentication(memberRepository.findMemberByEmail(member.email()));
        return jwtProvider.generateToken(member);
    }
}
