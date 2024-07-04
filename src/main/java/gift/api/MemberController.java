package gift.api;

import gift.application.MemberService;
import gift.dto.AuthResponse;
import gift.dto.MemberDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/register")
    public String showSignupView() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public void signUp(@RequestBody @Valid MemberDto memberDto) {
        memberService.registerUser(memberDto);
    }

    @GetMapping("/login")
    public String showLoginView() {
        return "login";
    }

    @PostMapping("/login/token")
    @ResponseBody
    public AuthResponse login(@RequestBody @Valid MemberDto memberDto) {
        String token = memberService.login(memberDto);
        return AuthResponse.of(token);
    }

}
