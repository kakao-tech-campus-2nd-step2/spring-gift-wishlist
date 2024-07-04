package gift.service;

import gift.domain.Member;
import gift.domain.MemberRepository;
import gift.error.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    public void addMember(Member member) {
        memberRepository.save(member);
    }

    public String login(String email, String password) {
        Member member = memberRepository.findByEmailAndPassword(email, password).orElseThrow(
            LoginException::new);

        return jwtService.create(member);
    }

}
