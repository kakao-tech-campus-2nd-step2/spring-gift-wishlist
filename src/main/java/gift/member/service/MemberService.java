package gift.member.service;

import gift.member.domain.Member;
import gift.member.repository.MemberRepository;
import gift.member.dto.MemberServiceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember(MemberServiceDto memberServiceDto) {
        memberRepository.save(memberServiceDto.toMember());
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
