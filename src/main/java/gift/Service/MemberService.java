package gift.Service;

import gift.Model.Member;
import gift.Model.Product;
import gift.Repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Member getMemberByEmail(String email){
        return memberRepository.findByEmail(email);
    }
    public void signupMember(Member member){
        memberRepository.addMember(member);
    }
}
