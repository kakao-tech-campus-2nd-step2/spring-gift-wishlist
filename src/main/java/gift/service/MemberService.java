package gift.service;

import gift.domain.Member;
import gift.repository.MemberRepository;
import gift.web.dto.request.member.CreateMemberRequest;
import gift.web.dto.response.member.CreateMemberResponse;
import gift.web.dto.response.member.ReadMemberResponse;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public CreateMemberResponse createMember(CreateMemberRequest request) {
        Member member = request.toEntity();
        return new CreateMemberResponse(memberRepository.save(member));
    }

    public ReadMemberResponse readMember(Long id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. id: " + id));

        return ReadMemberResponse.fromEntity(member);
    }
}
