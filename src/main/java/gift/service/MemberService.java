package gift.service;

import gift.config.JwtProvider;
import gift.domain.member.Member;
import gift.repository.MemberRepository;
import gift.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void addMember(Member member) {
        memberRepository.save(member);
    }

    public String login(String email, String password) {
        Member member = memberRepository.findByEmailAndPassword(email, password).orElseThrow(
            LoginException::new);

        return JwtProvider.create(member);
    }

}
