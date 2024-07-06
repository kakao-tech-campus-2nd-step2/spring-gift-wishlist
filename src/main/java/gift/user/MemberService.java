package gift.user;

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

    public void register(Member member) {
        if (memberRepository.existMemberByEmail(member.email())) {
            throw new IllegalArgumentException("User already exists");
        }

        memberRepository.addMember(member);
        jwtProvider.generateToken(member);
    }

    public void login(Member member) {
        if (!memberRepository.existMemberByEmail(member.email())) {
            throw new FailedLoginException("User does not exist");
        }

        Member findUser = memberRepository.findMemberByEmail(member.email());

        if (!findUser.password().equals(member.password())) {
            throw new FailedLoginException("Wrong password");
        }

        jwtProvider.generateToken(member);
    }
}
