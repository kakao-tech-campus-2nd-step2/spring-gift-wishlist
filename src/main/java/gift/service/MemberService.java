package gift.service;

import gift.dto.Member;
import gift.repository.MemberRepository;
import gift.repository.TokenRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;

    public MemberService(MemberRepository memberRepository, TokenRepository tokenRepository) {
        this.memberRepository = memberRepository;
        this.tokenRepository = tokenRepository;
    }

    public Long registerMember(Member member) {
        return memberRepository.registerMember(member);

    }


    public Long loginMember(Member member) {
        return memberRepository.getMemberIdByEmailAndPassword(member)
                .getId();
    }

    public boolean checkEmailDuplicate(String email) {
        return memberRepository.checkEmailDuplicate(email);
    }
}
