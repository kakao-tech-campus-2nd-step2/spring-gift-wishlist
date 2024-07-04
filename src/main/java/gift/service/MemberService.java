package gift.service;

import gift.controller.MemberController;
import gift.domain.Member;
import gift.domain.MemberRequest;
import gift.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public void join(MemberRequest memberRequest) {
        memberRepository.save(memberRequest);
    }

    public MemberRequest login(MemberRequest memberRequest) {
        return memberRepository.findById(memberRequest.id());
    }
}