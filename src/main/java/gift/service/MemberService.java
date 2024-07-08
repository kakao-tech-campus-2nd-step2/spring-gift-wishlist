package gift.service;

import gift.model.Member;
import gift.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member save(Member member) {
        // 비밀번호 암호화 코드 제거
        return memberRepository.save(member);
    }

    // 불필요한 loadUserByUsername 메서드 제거
}
