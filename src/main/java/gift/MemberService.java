package gift;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }*/

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> getMemberByEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        return member;
    }
    /*public void deleteMember(String email) {
        memberRepository.deleteByEmail(email);
    }*/
}
