package gift.user;

import gift.token.Token;
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
    public Token register(@Valid @RequestBody Member member) {
        return memberService.register(member);
    }

    @PostMapping("/login")
    public Token login(@Valid @RequestBody Member member) {
        return memberService.login(member);
    }
}
