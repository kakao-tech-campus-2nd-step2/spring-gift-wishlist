package gift.member;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody Member member) {
        memberService.register(member);
    }

    @PostMapping("/login")
    public void login(@Valid @MemberResolver Member member) {
        memberService.login(member);
    }
}
