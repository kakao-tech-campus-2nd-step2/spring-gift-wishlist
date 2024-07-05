package gift.service;

import gift.model.member.Member;
import gift.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private static final String SECRET_KEY = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Member registerNewMember(Member member){
        return memberRepository.save(member);
    }

    public boolean loginMember(Member member){
        Member registeredMember = memberRepository.findByEmail(member.email());
        if (registeredMember.equals(member)) {
            return true;
        }
        return false;
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
