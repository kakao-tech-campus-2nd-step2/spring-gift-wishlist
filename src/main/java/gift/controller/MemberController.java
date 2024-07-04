package gift.controller;

import gift.domain.MemberRequest;
import gift.domain.MenuRequest;
import gift.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public void join(
            @RequestParam("id") String id,
            @RequestParam("passwd") String passwd
    ) {
        System.out.println(id);
        MemberRequest memberRequest = new MemberRequest(id,passwd);
        memberService.join(memberRequest);
    }

}
