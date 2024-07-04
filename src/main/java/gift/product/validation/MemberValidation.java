package gift.product.validation;

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
}
