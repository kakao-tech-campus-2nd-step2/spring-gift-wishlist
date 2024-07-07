package gift.member;

import jakarta.servlet.http.HttpServletResponse;
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
    public void register(@Valid @RequestBody Member member, HttpServletResponse response) {
        response.setHeader("Authorization", memberService.register(member));
    }

    @PostMapping("/login")
    public void login(@Valid @MemberResolver Member member, HttpServletResponse response) {
        response.setHeader("Authorization", memberService.login(member));
    }
}
