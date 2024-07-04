package gift.service;

import gift.dto.MemberRequest;
import gift.dto.MemberResponse;
import gift.model.Member;
import gift.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public MemberResponse register(MemberRequest memberRequest) {
        Member member = createMemberWithMemberRequest(memberRequest);
        Member savedMember = repository.save(member);
        String token = createAccessTokenWithMember(savedMember);
        return MemberResponse.from(token);
    }

    private Member createMemberWithMemberRequest(MemberRequest memberRequest) {
        return new Member(memberRequest.name(), memberRequest.email(), memberRequest.password());
    }

    private String createAccessTokenWithMember(Member member) {
        return "savedToken";
    }
}
