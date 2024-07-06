package gift.controller;

import gift.dto.LoginToken;
import gift.dto.MemberDTO;
import gift.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PutMapping
    public void register(@RequestBody MemberDTO memberDTO) {
        memberService.register(memberDTO);
    }

    @GetMapping("/login")
    public LoginToken login(@RequestParam("email") String email,
        @RequestParam("password") String password) {
        return memberService.login(new MemberDTO(email, password, null));
    }
}
