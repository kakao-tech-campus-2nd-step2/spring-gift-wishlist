package gift.service;

import gift.controller.MemberController;
import gift.domain.MemberRequest;
import gift.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public void join(MemberRequest memberRequest) {
        memberRepository.save(memberRequest);
    }
}
