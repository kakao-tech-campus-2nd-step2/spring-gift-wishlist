package gift.product.validation;

import gift.product.exception.DuplicateEmail;
import gift.product.model.Member;
import gift.product.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberValidation {
    private final MemberService memberService;

    @Autowired
    public MemberValidation(MemberService memberService) {
        this.memberService = memberService;
    }

    public boolean validateMember(Member member) {
        System.out.println("[MemeberValidation] validateMember()");
        return memberService.isExistsMember(member);
    }

    public void emailDuplicateCheck(Member member) {
        System.out.println("[MemberValidation] emailDuplicateCheck()");
        if(memberService.isExistsMember(member))
            throw new DuplicateEmail("이미 가입된 회원입니다.");
    }
}
