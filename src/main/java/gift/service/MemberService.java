package gift.service;

import gift.domain.Member;
import gift.exception.InvalidCredentialsException;
import gift.exception.MemberNotFoundException;
import gift.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void register(String email, String password) {
        Member member = new Member(email, password);
        memberRepository.save(member);
    }

    public String authenticate(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        if (!member.getPassword().equals(password)) {
            throw new InvalidCredentialsException("잘못된 비밀번호입니다.");
        }

        // 실제로는 JWT 토큰을 생성하여 반환해야 하지만, 예시에서는 단순 문자열을 반환하도록 하였습니다.
        return "Authentication successful for " + email;
    }
}
