package gift.service;

import gift.dto.request.MemberRequest;
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

    public Long registerMember(MemberRequest member) {
        return memberRepository.registerMember(member);
    }


    public Long loginMember(MemberRequest member) {
        return memberRepository.getMemberIdByEmailAndPassword(member);
    }

    public boolean checkEmailDuplicate(String email) {
        return memberRepository.checkEmailDuplicate(email);
    }

}
